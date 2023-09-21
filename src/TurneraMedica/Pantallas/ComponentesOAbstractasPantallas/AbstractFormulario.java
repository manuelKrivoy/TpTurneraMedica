package TurneraMedica.Pantallas.ComponentesOAbstractasPantallas;

import TurneraMedica.Pantallas.PanelManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
public abstract class AbstractFormulario extends JPanel {
    protected PanelManager panelManager;
    protected CamposPanel camposPanel;
    protected BotoneraFormulario botoneraFormulario;

    public AbstractFormulario() {

        this.setCamposPanel();
        this.setBotoneraPanel();
        armarPantallaFormulario();
    }

    public void armarPantallaFormulario() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        gbc.fill = GridBagConstraints.BOTH;
        add(camposPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.1;
        add(botoneraFormulario, gbc);

        botoneraFormulario.getOkBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionOk();
            }
        });

        botoneraFormulario.getVolverBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionVolver();
            }
        });

        botoneraFormulario.getHomeBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionHome();
            }
        });

    }

    private void setBotoneraPanel() {
        this.botoneraFormulario = new BotoneraFormulario(this.panelManager);
    }
    public abstract void setCamposPanel();
    public abstract void ejecutarAccionOk();
    public abstract void ejecutarAccionVolver();

    public void ejecutarAccionHome(){
        panelManager.mostrarMenuPrincipal();
    }

}
