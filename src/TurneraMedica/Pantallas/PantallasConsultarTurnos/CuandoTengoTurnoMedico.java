package TurneraMedica.Pantallas.PantallasConsultarTurnos;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Medico.MedicoService;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.AbstractFormulario;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.CamposPanel;
import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;
import java.awt.*;

public class CuandoTengoTurnoMedico extends AbstractFormulario {

    protected PanelManager panelManager;
    private JTextField matriculaField;

    private static final int MAX_PERMITIDO_MATRICULA = 999999 ;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposPanel(panelManager) {
            @Override
            public void armarFormulario() {
                this.setLayout(new GridLayout(1, 2));
                this.setBackground(Color.cyan);
                JLabel matriculaLabel = new JLabel("Matricula: ");
                matriculaField = new JTextField(10);

                this.add(matriculaLabel);
                this.add(matriculaField);
            }
        };
    }
    @Override
    public void ejecutarAccionOk() {
        try{
            int matricula = Integer.parseInt(matriculaField.getText());
            MedicoService service = new MedicoService();
            if(matricula > MAX_PERMITIDO_MATRICULA) throw new IllegalArgumentException("La matricula es demasiado larga");
            if(!service.encuentraMedico(matricula)) throw  new IllegalArgumentException("La matricula no está registrada en el sistema.");
            panelManager.tablaTurnosAsignadosMedico(matricula);
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Asegúrate de poner números en matrícula", "ERROR", JOptionPane.ERROR_MESSAGE);
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
