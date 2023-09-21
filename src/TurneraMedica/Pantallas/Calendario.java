package TurneraMedica.Pantallas;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Turnos.Turno;
import TurneraMedica.Turnos.TurnoService;
import TurneraMedica.Utilidades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Calendario extends JFrame {

    protected PanelManager panelManager;
    private JLabel label;
    private JButton botonAtras;
    private JButton botonSiguiente;
    private JPanel calendarioPanel;
    private int mesActual;
    private String[] nombresMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public Calendario() {
        botonAtras = new JButton("Anterior mes");
        botonSiguiente = new JButton("Siguiente mes");
        calendarioPanel = new JPanel(new GridLayout(0, 7));
        mesActual = 1;

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mesActual > 1) {
                    mesActual--;
                    refrescarCalendario();
                }
                else{
                    mesActual=12; //si esta en enero y se toca anterior va a diciembre
                    refrescarCalendario();
                }
            }
        });

        botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mesActual < 12) {
                    mesActual++;
                    refrescarCalendario();
                }
                else{
                    mesActual=1; //si está en diciembre y se toca siguiente va a enero
                    refrescarCalendario();
                }
            }
        });

        JPanel botoneraPanel = new JPanel();
        botoneraPanel.add(botonAtras);
        botoneraPanel.add(botonSiguiente);
        label = new JLabel("Calendario ");
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(label, BorderLayout.NORTH);
        panelPrincipal.add(calendarioPanel, BorderLayout.CENTER);
        panelPrincipal.add(botoneraPanel, BorderLayout.SOUTH);

        add(panelPrincipal);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        refrescarCalendario();
    }

    private void refrescarCalendario() {
        calendarioPanel.removeAll();
        calendarioPanel.revalidate();
        label.setText("Calendario " + nombresMeses[mesActual-1]);

        TurnoService turnoService = new TurnoService();
        List<Turno> turnos = null;
        try {
            turnos = turnoService.listaTodosLosTurnos();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al listar turnos! (SQL EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        for (int i = 1; i <= 31; i++) {
            JButton botonDia = new JButton(Integer.toString(i));
            botonDia.setHorizontalAlignment(SwingConstants.CENTER);
            botonDia.setEnabled(false);

            if (turnos != null) {
                for (Turno turno : turnos) {
                    String fecha = turno.getFecha();
                    int turnoDay = Utilidades.extraerDia(fecha);
                    int turnoMonth = Utilidades.extraerMes(fecha);

                    if (turnoDay == i && turnoMonth == mesActual) {
                        botonDia.setBackground(Color.MAGENTA);
                        botonDia.setForeground(Color.white);
                        botonDia.setEnabled(true);
                        botonDia.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panelManager.mostrarListaTurnos(fecha);
                            }
                        });

                        break;
                    }
                }
            }

            calendarioPanel.add(botonDia);
        }

        pack();
    }
}
