package TurneraMedica.Pantallas.PantallasConsultarTurnos;

import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarTurnos extends JPanel{

    protected PanelManager panelManager;

    private JButton botonConsultaMedico;
    private JButton botonConsultaPaciente;

    private JButton botonAtras;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public ConsultarTurnos(){
        this.setLayout(new FlowLayout());
        this.setBackground(Color.lightGray);

        botonConsultaMedico= new JButton("Consultar turnos medico");
        botonConsultaMedico.setBackground(Color.cyan);
        botonConsultaPaciente= new JButton("Consultar turnos paciente");
        botonConsultaPaciente.setBackground(Color.pink);
        botonAtras = new JButton("Atras");
        botonAtras.setBackground(Color.gray);

        botonConsultaMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelManager.cuandoTengoTurnoMedico();
            }
        });

        botonConsultaPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelManager.cuandoTengoTurnoPaciente();
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {panelManager.mostrarMenuPrincipal();}
        });

        this.add(botonConsultaMedico);
        this.add(botonConsultaPaciente);
        this.add(botonAtras);


    }
}
