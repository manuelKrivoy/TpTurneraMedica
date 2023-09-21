package TurneraMedica.Pantallas.PantallasMedico;

import javax.swing.*;


import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Medico.Medico;
import TurneraMedica.Medico.MedicoService;

import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.AbstractFormulario;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.CamposPanel;
import TurneraMedica.Pantallas.PanelManager;
import TurneraMedica.Utilidades;

import TurneraMedica.ObraSocial.ObraSocial;

import java.awt.*;

public class FormularioMedico extends AbstractFormulario {
    private static final int MAX_PERMITIDO_NOMBRE = 18 ;
    private static final int MAX_PERMITIDO_MATRICULA = 6 ;
    private static final int MAX_PERMITIDO_CONSULTA = 7000 ;
    private static final int MIN_PERMITIDO_CONSULTA = 500 ;
    private JTextField matriculaField;
    private JTextField nombreField;
    private JComboBox<String> obrasSocialesOpciones;
    private JTextField valorConsultaField;
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
                this.setBackground(Color.CYAN);
                JLabel matriculaLabel = new JLabel("Matrícula: ");
                matriculaField = new JTextField(10);

                JLabel nombreLabel = new JLabel("Nombre: ");
                nombreField = new JTextField(10);

                ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
                String[] obrasSocialesString = new String[obrasSociales.length];
                for (int i = 0; i < obrasSociales.length; i++) {
                    obrasSocialesString[i] = obrasSociales[i].getNombre_os();
                }
                obrasSocialesOpciones  = new JComboBox<>(obrasSocialesString);

                JLabel valorConsultaLabel = new JLabel("Valor Consulta: ");
                valorConsultaField = new JTextField(10);

                this.add(matriculaLabel);
                this.add(matriculaField);
                this.add(nombreLabel);
                this.add(nombreField);
                this.add(new JLabel("Obra Social: "));
                this.add(obrasSocialesOpciones);
                this.add(valorConsultaLabel);
                this.add(valorConsultaField);
            }
        };
    }

    @Override
    public void ejecutarAccionOk() {
        try {
            String textoDeObraSocial = obrasSocialesOpciones.getSelectedItem().toString();
            ObraSocial obraSocial = Utilidades.convertirEnObraSocial(textoDeObraSocial);
            if (nombreField.getText().length() == 0) {
            throw new IllegalArgumentException("Sí o sí debes ingresar un nombre");
            }
            if (matriculaField.getText().length() == 0) {
                throw new IllegalArgumentException("Sí o sí  debes ingresar una matricula");
            }
            if (nombreField.getText().length() > MAX_PERMITIDO_NOMBRE) {
                throw new IllegalArgumentException("El nombre es demasiado largo");
            }

            if (matriculaField.getText().length() > MAX_PERMITIDO_MATRICULA) {
                throw new IllegalArgumentException("La matricula es demasiada larga");
            }
            if ((Integer.parseInt(valorConsultaField.getText()) > MAX_PERMITIDO_CONSULTA) || (Integer.parseInt(valorConsultaField.getText()) < MIN_PERMITIDO_CONSULTA))  {
                throw new IllegalArgumentException("El valor de consulta no entra dentro de los parametros aceptados. ($500 - $7000) ");
            }

            int matricula = Integer.parseInt(matriculaField.getText()); //Esto tira NumberFormatException si no se puede hacer
            int valorConsulta = Integer.parseInt(valorConsultaField.getText()); //Esto tira NumberFormatException si no se puede hacer

            Medico medico = new Medico(matricula, nombreField.getText(), obraSocial, valorConsulta, 0);
            MedicoService medicoService = new MedicoService();
            medicoService.crearMedico(medico);

            JOptionPane.showMessageDialog(null, "Datos del médico cargados:\n"
                    + "Matrícula: " + matriculaField.getText() + "\n"
                    + "Nombre: " + nombreField.getText() + "\n"
                    + "Obra Social: " + obrasSocialesOpciones.getSelectedItem().toString().toUpperCase() + "\n"
                    + "Valor Consulta: $" + valorConsultaField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Asegúrate de poner números en matrícula y valor consulta", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, "El médico ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void ejecutarAccionVolver() {
        panelManager.mostrarMenuMedico();
    }


    public void ejecutarAccionHome(){
        panelManager.mostrarMenuPrincipal(); //ENORME DUDA DE PORQUE NO ANDA SI LA DEFINO EN ABSTRACT
    }

}
