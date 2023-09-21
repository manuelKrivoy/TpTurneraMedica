package TurneraMedica.Pantallas.PantallasMedico;

import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.Menu;

import java.awt.*;

public class MenuMedico extends Menu {

    public MenuMedico() {
        super("medico",Color.cyan);
    }

    @Override
    public void onAgregar() {
        panelManager.mostrarFormularioMedico();
    }

    @Override
    public void onVer() {
        panelManager.mostrarListaMedicos();
    }
}

