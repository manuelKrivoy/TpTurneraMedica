package TurneraMedica.Pantallas;

import TurneraMedica.Pantallas.PantallasConsultarTurnos.ConsultarTurnos;
import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Medico.Medico;
import TurneraMedica.Medico.MedicoService;
import TurneraMedica.ObraSocial.ObraSocial;

import TurneraMedica.Paciente.Paciente;
import TurneraMedica.Paciente.PacienteService;

import TurneraMedica.Turnos.Turno;
import TurneraMedica.Turnos.TurnoService;

import TurneraMedica.Pantallas.PantallasMedico.FormularioMedico;
import TurneraMedica.Pantallas.PantallasMedico.ListaMedicos;
import TurneraMedica.Pantallas.PantallasMedico.MenuMedico;

import TurneraMedica.Pantallas.PantallasPaciente.MenuPaciente;
import TurneraMedica.Pantallas.PantallasPaciente.FormularioPaciente;
import TurneraMedica.Pantallas.PantallasPaciente.ListaPacientes;

import TurneraMedica.Pantallas.PantallasTurnos.MenuTurnos;
import TurneraMedica.Pantallas.PantallasTurnos.FormularioTurnos;
import TurneraMedica.Pantallas.PantallasTurnos.ListaTurnos;

import TurneraMedica.Pantallas.PantallasConsultarTurnos.CuandoTengoTurnoMedico;
import TurneraMedica.Pantallas.PantallasConsultarTurnos.CuandoTengoTurnoPaciente;

import TurneraMedica.Utilidades;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class PanelManager {

    private JFrame frame;
    private MenuPrincipal menuPrincipal;

    private MenuMedico menuMedico;
    private FormularioMedico formularioMedico;
    private ListaMedicos listaMedicos;

    private MenuPaciente menuPaciente;
    private FormularioPaciente formularioPaciente;
    private ListaPacientes listaPacientes;

    private MenuTurnos menuTurnos;
    private FormularioTurnos formularioTurnos;
    private ListaTurnos listaTurnos;

    private ConsultarTurnos consultarTurnos;
    private CuandoTengoTurnoMedico cuandoTengoTurnoMedico;
    private CuandoTengoTurnoPaciente cuandoTengoTurnoPaciente;

    private CuantoHaGanado cuantoHaGanado;
    private Calendario calendario;

    public void armarManager() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 500); //seteo medidas de frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Para que salga mensaje final
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pantallaDespedida();
                frame.dispose();
            }
        };

        frame.addWindowListener(windowListener);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        menuPrincipal = new MenuPrincipal();
        menuMedico = new MenuMedico();
        formularioMedico = new FormularioMedico();

        menuPaciente = new MenuPaciente();
        formularioPaciente = new FormularioPaciente();

        menuTurnos = new MenuTurnos();
        formularioTurnos = new FormularioTurnos();

        consultarTurnos = new ConsultarTurnos();
        cuandoTengoTurnoMedico= new CuandoTengoTurnoMedico();
        cuandoTengoTurnoPaciente = new CuandoTengoTurnoPaciente();

        cuantoHaGanado = new CuantoHaGanado();

    }

    private static void pantallaDespedida() {
        JFrame pantallaDespedida = new JFrame("El programa ha finalizado");
        pantallaDespedida.setSize(500, 500);
        pantallaDespedida.getContentPane().setBackground(Color.darkGray); // Cambia el fondo a gris claro

        Font font = new Font("Comic Sans MS", Font.BOLD, 16);

        JLabel label = new JLabel("<html>Programa creado por Manuel Krivoy<br><br>Legajo: 0125450"
                + "<br><br>Materia: Programación 3<br><br>Profesor: Guido Chiesa</html>");
        label.setFont(font);
        label.setForeground(Color.yellow); // Cambia el color del texto a blanco
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Hace que el fondo del panel sea transparente
        panel.add(label);

        pantallaDespedida.add(panel);
        pantallaDespedida.setLocationRelativeTo(null);
        pantallaDespedida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaDespedida.setResizable(false);

        pantallaDespedida.setVisible(true);
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public void mostrarMenuPrincipal() {
        Utilidades.mostrarPantalla(frame, menuPrincipal);
        menuPrincipal.setPanelManager(this);
    }

    public void mostrarMenuMedico() {
        Utilidades.mostrarPantalla(frame, menuMedico);
        menuMedico.setPanelManager(this);
    }

    public void mostrarListaMedicos() {
        ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
        MedicoService medicoService = new MedicoService();
        List<Medico> medicos = null;
        try {  //esta excepcion no la llevamos
            medicos = medicoService.listaTodosLosMedicos(obrasSociales);
        } catch (ServiceException e) { //esta excepcion no la llevamos a capa ui, pero si la tratamos como tal
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar medicos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        listaMedicos = new ListaMedicos(medicos);
        listaMedicos.setPanelManager(this);
        listaMedicos.setVisible(true);
    }

    public void mostrarFormularioMedico() {
        Utilidades.mostrarPantalla(frame, formularioMedico);
        formularioMedico.setPanelManager(this);
    }

    public void mostrarMenuPaciente() {
        Utilidades.mostrarPantalla(frame, menuPaciente);
        menuPaciente.setPanelManager(this);
    }

    public void mostrarFormularioPaciente() {
        Utilidades.mostrarPantalla(frame, formularioPaciente);
        formularioPaciente.setPanelManager(this);
    }

    public void mostrarListaPacientes() {
        ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
        PacienteService pacienteService = new PacienteService();
        List<Paciente> pacientes = null;
        try {
            pacientes = pacienteService.listaTodosLosPacientes(obrasSociales);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar medicos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        listaPacientes = new ListaPacientes(pacientes);
        listaPacientes.setPanelManager(this);
        listaPacientes.setVisible(true);
    }

    public void mostrarMenuTurnos() {
        Utilidades.mostrarPantalla(frame, menuTurnos);
        menuTurnos.setPanelManager(this);
    }

    public void mostrarFormularioTurnos() {
        Utilidades.mostrarPantalla(frame, formularioTurnos);
        formularioTurnos.setPanelManager(this);
    }

    public void mostrarListaTurnos() {
        TurnoService turnoService = new TurnoService();
        List<Turno> turnos = null;
        try {
            turnos = turnoService.listaTodosLosTurnos();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar turnos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        listaTurnos = new ListaTurnos(turnos);
        listaTurnos.setPanelManager(this);
        listaTurnos.setVisible(true);
    }

    public void mostrarConsultaTurnos() {
        Utilidades.mostrarPantalla(frame, consultarTurnos);
        consultarTurnos.setPanelManager(this);
    }

    public void cuandoTengoTurnoMedico(){
        Utilidades.mostrarPantalla(frame, cuandoTengoTurnoMedico);
        cuandoTengoTurnoMedico.setPanelManager(this);
    }

    public void tablaTurnosAsignadosMedico(int matricula){
        TurnoService turnoService = new TurnoService();
        List<Turno> turnos = null;
        try {
            turnos = turnoService.listaCuandoTengoTurnoMedico(matricula);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar turnos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        listaTurnos = new ListaTurnos(turnos);
        listaTurnos.setPanelManager(this);
        listaTurnos.setVisible(true);
    }

    public void cuandoTengoTurnoPaciente(){
        Utilidades.mostrarPantalla(frame, cuandoTengoTurnoPaciente);
        cuandoTengoTurnoPaciente.setPanelManager(this);
    }

    public void tablaTurnosAsignadosPaciente(int DNI){
        TurnoService turnoService = new TurnoService();
        List<Turno> turnos = null;
        try {
            turnos = turnoService.listaCuandoTengoTurnoPaciente(DNI);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar turnos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        listaTurnos = new ListaTurnos(turnos);
        listaTurnos.setPanelManager(this);
        listaTurnos.setVisible(true);
    }

    public void mostrarCuantoHaGanado(){
        Utilidades.mostrarPantalla(frame, cuantoHaGanado);
        cuantoHaGanado.setPanelManager(this);
    }

    public void mostrarCalendario() {
        calendario = new Calendario();
        calendario.setPanelManager(this);
        calendario.setVisible(true);
    }

    public void mostrarListaTurnos(String fecha) { // queria hacer sobreCarga para utilizarlo alguna vez en el programa. podría haber filtrado la lista directamente desde la BDD con un select where pero preferí trabajarlo dsede la lista completa.
        TurnoService turnoService = new TurnoService();
        List<Turno> turnos = null;
        try {
            turnos = turnoService.listaTodosLosTurnos();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar turnos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        List <Turno> turnosFecha = null;
        turnosFecha = Utilidades.extraerTurnosFecha(turnos,fecha);
        listaTurnos = new ListaTurnos(turnosFecha);
        listaTurnos.setPanelManager(this);
        listaTurnos.setVisible(true);
    }

}

