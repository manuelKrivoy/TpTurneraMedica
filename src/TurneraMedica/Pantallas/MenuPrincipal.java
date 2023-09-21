package TurneraMedica.Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MenuPrincipal extends JPanel {
    protected PanelManager panelManager;
    private JButton botonMedico;
    private JButton botonPaciente;
    private JButton botonTurnos;
    private JButton botonCuantoTengoTurno;
    private JButton botonCuantoHaGanado;
    private JButton botonCalendario;

    public void setPanelManager(PanelManager panelManager) { //seter de panel manager
        this.panelManager = panelManager;
    }
    public MenuPrincipal() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.lightGray);

        //creación de botones
        botonMedico = new JButton("Médico");
        botonPaciente = new JButton("Paciente");
        botonTurnos = new JButton("Turnos");
        botonCuantoTengoTurno = new JButton("Consultar turnos");
        botonCuantoHaGanado = new JButton("¿Cuánto ha ganado?");
        botonCalendario = new JButton("Calendario de turnos");

        //Diseño de botones
        Font arial = new Font("Arial", Font.BOLD, 12);
        botonMedico.setFont(arial);
        botonPaciente.setFont(arial);
        botonTurnos.setFont(arial);
        botonCuantoTengoTurno.setFont(arial);
        botonCuantoHaGanado.setFont(arial);
        botonCalendario.setFont(arial);
        botonMedico.setBackground(Color.cyan);
        botonPaciente.setBackground(Color.PINK);
        botonTurnos.setBackground(Color.orange);
        botonCuantoTengoTurno.setBackground(Color.green);
        botonCuantoHaGanado.setBackground(Color.YELLOW);
        botonCalendario.setBackground(Color.magenta);

        //Action Listeners de botones
        botonMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarMenuMedico();
            }
        });

        botonPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarMenuPaciente();
            }
        });

        botonTurnos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {panelManager.mostrarMenuTurnos();}
        });

        botonCuantoTengoTurno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {panelManager.mostrarConsultaTurnos();}
        });

        botonCuantoHaGanado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {panelManager.mostrarCuantoHaGanado();}
        });

        botonCalendario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {panelManager.mostrarCalendario();}
        });

        //agrego botones a pantalla
        this.add(botonMedico);
        this.add(botonPaciente);
        this.add(botonTurnos);
        this.add(botonCuantoTengoTurno);
        this.add(botonCuantoHaGanado);
        this.add(botonCalendario);
    }
}