package TurneraMedica.Pantallas.PantallasTurnos;

import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.Menu;

import java.awt.*;
public class MenuTurnos extends Menu {
    private FormularioTurnos formularioTurnos;
    public MenuTurnos() {
    super("turno",Color.orange);
    this.formularioTurnos = formularioTurnos;

    }

    @Override
    public void onAgregar() {
        this.panelManager.mostrarFormularioTurnos();
    }

    @Override
    public void onVer() {
        panelManager.mostrarListaTurnos();
    }
}
