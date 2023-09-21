package TurneraMedica.Pantallas.PantallasConsultarTurnos;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Paciente.PacienteService;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.AbstractFormulario;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.CamposPanel;
import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;
import java.awt.*;

public class CuandoTengoTurnoPaciente extends AbstractFormulario {
    protected PanelManager panelManager;
    private JTextField DNIField;
    private static final int MAX_PERMITIDO_DNI = 999999999  ;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposPanel(panelManager) {
            @Override
            public void armarFormulario() {
                this.setLayout(new GridLayout(1, 2));
                this.setBackground(Color.pink);
                JLabel DNILabel = new JLabel("DNI: ");
                DNIField = new JTextField(10);

                this.add(DNILabel);
                this.add(DNIField);
            }
        };
    }
    @Override
    public void ejecutarAccionOk() {
        try{
            int dni = Integer.parseInt(DNIField.getText());
            PacienteService service = new PacienteService();
            if(dni > MAX_PERMITIDO_DNI) throw new IllegalArgumentException("El DNI es demasiado largo");
            if(!service.encuentraPaciente(dni)) throw  new IllegalArgumentException("El DNI no está registrada en el sistema.");
             panelManager.tablaTurnosAsignadosPaciente(dni);

        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Asegúrate de poner números en el dni", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, "", "ERROR al buscar la matricula del medico", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void ejecutarAccionVolver() {
        panelManager.mostrarConsultaTurnos();
    }

    public void ejecutarAccionHome(){
        panelManager.mostrarMenuPrincipal(); //ENORME DUDA DE PORQUE NO ANDA SI LA DEFINO EN ABSTRACT
    }
}
