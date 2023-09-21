package TurneraMedica.Pantallas.ComponentesOAbstractasPantallas;

import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;
import java.awt.Color;


public  class BotoneraFormulario extends JPanel {

    protected PanelManager panelManager;
    private JButton okBtn;
    private JButton volverBtn;
    private JButton homeBtn;

    public BotoneraFormulario(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.armarBotoneraFormulario();
    }

    public void armarBotoneraFormulario() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
        okBtn = new JButton("OK");
        volverBtn = new JButton("VOLVER");
        homeBtn = new JButton("HOME");

        add(Box.createHorizontalStrut(10));
        add(okBtn);
        add(Box.createHorizontalStrut(10));
        add(volverBtn);
        add(Box.createHorizontalStrut(10));
        add(homeBtn);

        okBtn.setBackground(Color.BLACK);
        okBtn.setForeground(Color.WHITE);

        volverBtn.setBackground(Color.BLACK);
        volverBtn.setForeground(Color.WHITE);

        homeBtn.setBackground(Color.BLACK);
        homeBtn.setForeground(Color.WHITE);

    }

    public JButton getHomeBtn() {
        return homeBtn;
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public JButton getVolverBtn() {
        return volverBtn;
    }

}
