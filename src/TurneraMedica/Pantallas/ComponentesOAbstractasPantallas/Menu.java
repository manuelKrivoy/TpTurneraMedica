package TurneraMedica.Pantallas.ComponentesOAbstractasPantallas;

import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Menu extends JPanel {

    protected PanelManager panelManager;
    private JButton botonAgregar;
    private JButton botonVer;
    private JButton botonAtras;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public Menu(String tipoMenu, Color color) {
        this.setLayout(new FlowLayout());
        this.setBackground(color);

        botonAgregar = new JButton("Agregar " + tipoMenu );
        botonVer = new JButton("Ver "+tipoMenu+"s");
        botonAtras = new JButton("Volver");

        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAgregar();
            }
        });

        botonVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onVer();
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarMenuPrincipal();
            }
        });

        this.add(botonAgregar);
        this.add(botonVer);
        this.add(botonAtras);
    }

    public abstract void onAgregar();

    public abstract void onVer();
}
