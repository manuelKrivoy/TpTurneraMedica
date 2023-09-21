package TurneraMedica.Pantallas.PantallasPaciente;

import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.Menu;

import java.awt.*;
public class MenuPaciente extends Menu {
    public MenuPaciente() {
        super("paciente",Color.PINK);
    }

        @Override
        public void onAgregar() {
            this.panelManager.mostrarFormularioPaciente();
        }

        @Override
        public void onVer() {
            this.panelManager.mostrarListaPacientes();
        }
    }


