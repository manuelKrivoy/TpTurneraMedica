package TurneraMedica.Pantallas.PantallasPaciente;

import TurneraMedica.Paciente.Paciente;
import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Paciente.PacienteService;

import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.AbstractFormulario;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.CamposPanel;
import TurneraMedica.Pantallas.PanelManager;
import TurneraMedica.Utilidades;


import TurneraMedica.ObraSocial.ObraSocial;

import javax.swing.*;
import java.awt.*;

public class FormularioPaciente extends AbstractFormulario {
    private static final int MAX_PERMITIDO_NOMBRE = 15 ;
    private static final int MAX_PERMITIDO_DNI = 9 ;

    private JTextField DNIField;
    private JTextField nombreField;
    private JComboBox<String> obrasSocialesOpciones;
    private JTextField emailField;
    protected PanelManager panelManager;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposPanel(panelManager) {
            @Override
            public void armarFormulario() {
                this.setLayout(new GridLayout(4, 2));
                this.setBackground(Color.PINK);
                JLabel DNILabel = new JLabel("DNI: ");
                DNIField = new JTextField(10);

                JLabel nombreLabel = new JLabel("Nombre: ");
                nombreField = new JTextField(10);

                ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
                String[] obrasSocialesString = new String[obrasSociales.length];
                for (int i = 0; i < obrasSociales.length; i++) {
                    obrasSocialesString[i] = obrasSociales[i].getNombre_os();
                }

                obrasSocialesOpciones  = new JComboBox<>(obrasSocialesString);

                JLabel emailLabel = new JLabel("Email: ");
                emailField = new JTextField(10);

                this.add(DNILabel);
                this.add(DNIField);
                this.add(nombreLabel);
                this.add(nombreField);
                this.add(new JLabel("Obra Social: "));
                this.add(obrasSocialesOpciones);
                this.add(emailLabel);
                this.add(emailField);
            }
        };
    }

    @Override
    public void ejecutarAccionOk() {
        try {
            String textoDeObraSocial = obrasSocialesOpciones.getSelectedItem().toString();
            ObraSocial obraSocial = Utilidades.convertirEnObraSocial(textoDeObraSocial);

            if (nombreField.getText().length() == 0) {
                throw new IllegalArgumentException("Sí o sí  debes ingresar un nombre");
            }
            if (DNIField.getText().length() == 0) {
                throw new IllegalArgumentException("Sí o sí  debes ingresar un DNI");
            }
            if (nombreField.getText().length() > MAX_PERMITIDO_NOMBRE) {
                throw new IllegalArgumentException("El nombre es demasiado largo");
            }

            if (DNIField.getText().length() > MAX_PERMITIDO_DNI) {
                throw new IllegalArgumentException("El DNI es demasiada larga");
            }

            if(!emailField.getText().contains("@")){
                throw new IllegalArgumentException("El email debe contener un @");
            }

            int dni = Integer.parseInt(DNIField.getText()); //Esto tira NumberFormatException si no se puede hacer

            Paciente paciente = new Paciente(dni, nombreField.getText(), obraSocial, emailField.getText());
            PacienteService pacienteService = new PacienteService();
            pacienteService.crearPaciente(paciente);

            JOptionPane.showMessageDialog(null, "Datos del paciente cargados:\n"
                    + "DNI: " + DNIField.getText() + "\n"
                    + "Nombre: " + nombreField.getText() + "\n"
                    + "Obra Social: " + obrasSocialesOpciones.getSelectedItem().toString().toUpperCase() + "\n"
                    + "Email: " + emailField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Asegúrate de poner números en DNI", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, "El paciente ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void ejecutarAccionVolver() {
        panelManager.mostrarMenuPaciente();
    }


    public void ejecutarAccionHome(){
        panelManager.mostrarMenuPrincipal(); //ENORME DUDA DE PORQUE NO ANDA SI LA DEFINO EN ABSTRACT
    }
}
