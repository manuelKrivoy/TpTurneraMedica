package TurneraMedica.Pantallas.ComponentesOAbstractasPantallas;

import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;

public abstract class CamposPanel extends JPanel {
    private PanelManager m;

    public CamposPanel(PanelManager m) {
        this.m = m;
        armarFormulario();
    }

    public abstract void armarFormulario();
}
