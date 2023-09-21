package TurneraMedica.Pantallas;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Medico.MedicoService;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.AbstractFormulario;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.CamposPanel;
import TurneraMedica.Turnos.Turno;
import TurneraMedica.Turnos.TurnoService;

import TurneraMedica.Utilidades;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class CuantoHaGanado extends AbstractFormulario {

    protected PanelManager panelManager;
    private JTextField matriculaField;
    private JComboBox<Integer> mesDesdeOpciones;
    private JComboBox<Integer> mesHastaOpciones;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void setCamposPanel() {
        this.camposPanel = new CamposPanel(panelManager) {
            @Override
            public void armarFormulario() {
                this.setLayout(new GridLayout(3, 2));
                this.setBackground(Color.yellow);
                JLabel matriculaLabel = new JLabel("Matrícula: ");
                matriculaField = new JTextField(10);
                Integer[] opcionesMes = new Integer[12];
                for (int i = 0; i < 12; i++) {
                    opcionesMes[i] = i + 1;
                }
                JLabel mesD = new JLabel("Mes desde: ");
                mesDesdeOpciones= new JComboBox<>(opcionesMes);

                JLabel mesH = new JLabel("Mes hasta: ");
                mesHastaOpciones= new JComboBox<>(opcionesMes);

                this.add(matriculaLabel);
                this.add(matriculaField);
                this.add(mesD);
                this.add(mesDesdeOpciones);
                this.add(mesH);
                this.add(mesHastaOpciones);

            }
        };
    }
    public void ejecutarAccionOk() {
        try{
            if (matriculaField.getText().length() == 0) {
                throw new IllegalArgumentException("Sí o sí debes ingresar una matricula");
            }

            int matricula = Integer.parseInt(matriculaField.getText());
            MedicoService medicoService = new MedicoService();

            if (!medicoService.encuentraMedico(matricula)) {
                throw new IllegalArgumentException("La matricula no representa la de algún medico registrado");
            }

            TurnoService turnoService = new TurnoService();
            List<Turno> turnos = null;
            turnos = turnoService.listaTodosLosTurnos();
            int recaudado=0;
            int cantidad= 0;
            int mesDesde = Integer.parseInt(mesDesdeOpciones.getSelectedItem().toString());
            int mesHasta = Integer.parseInt(mesHastaOpciones.getSelectedItem().toString());

            for (Turno turno : turnos) {
                int mes = Utilidades.extraerMes(turno.getFecha());
                if(matricula == turno.getMatricula() && mes >= mesDesde && mes <= mesHasta ){
                    recaudado += turno.getImporteTurno();
                    cantidad++;
                }
            }
            if(cantidad != 0){
                JOptionPane.showMessageDialog(null, " Matricula: " +matricula+ "\n Ha recaudado: $ " +recaudado+ " en " +cantidad+ " turnos \n Desde el mes : " +mesDesde +"\n Hasta el mes: " +mesHasta);
            }else{
                JOptionPane.showMessageDialog(null, " Matricula: " +matricula+ "\n NO HA TENIDO TURNOS  \n Desde el mes : " +mesDesde +"\n Hasta el mes: " +mesHasta);
            }



        }catch(ServiceException ex){
            JOptionPane.showMessageDialog(null, "Ha habido un error al validar ejecutar la tarea", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ejecutarAccionVolver() {
        panelManager.mostrarMenuPrincipal();
    }

    @Override
    public void ejecutarAccionHome(){
        panelManager.mostrarMenuPrincipal(); //ENORME DUDA DE PORQUE NO ANDA SI LA DEFINO EN ABSTRACT
    }

}
