//PROGRAMA ELABORADO POR MANUEL KRIVOY

package TurneraMedica;

import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.Paciente.TableManagerPaciente;
import TurneraMedica.Medico.TableManagerMedico;
import TurneraMedica.Turnos.TableManagerTurno;
import TurneraMedica.Pantallas.PanelManager;


public class Main  {
    private PanelManager panel;
    public static void main(String[] args) throws ObjectoDuplicadoException {

        //CREO TABLAS PACIENTE Y MEDICOS
        TableManagerPaciente tp = new TableManagerPaciente();
        tp.createPacienteTable();
        TableManagerMedico tm= new TableManagerMedico();
        tm.createMedicoTable();
        TableManagerTurno tt= new TableManagerTurno();
        tt.createTurnoTable();

        Main main = new Main();
        main.iniciarManager();
        main.showFrame();

        // tm.dropTable();
        // tp.dropTable();
        //tt.dropTable();
    }
    public void iniciarManager() {
        panel = new PanelManager();
        panel.armarManager();
        panel.mostrarMenuPrincipal();
    }
    public void showFrame() {
        panel.showFrame();
    }

}