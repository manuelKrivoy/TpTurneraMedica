package TurneraMedica.Pantallas.PantallasTurnos;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Medico.MedicoService;
import TurneraMedica.Medico.Medico;
import TurneraMedica.ObraSocial.ObraSocial;
import TurneraMedica.Paciente.PacienteService;
import TurneraMedica.Turnos.Turno;
import TurneraMedica.Turnos.TurnoService;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.AbstractFormulario;
import TurneraMedica.Pantallas.ComponentesOAbstractasPantallas.CamposPanel;
import TurneraMedica.Pantallas.PanelManager;
import TurneraMedica.Utilidades;

import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioTurnos extends AbstractFormulario {
    private JComboBox<Integer> diaOpciones;
    private JComboBox<Integer> mesOpciones;
    private JComboBox<Integer> horaOpciones;
    private JComboBox<Integer> opcionesMatriculas;
    private JComboBox<Integer> opcionesDNI;
    private JComboBox<String> consultorioOpciones;

    protected PanelManager panelManager;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void setCamposPanel() {
        this.camposPanel = new CamposPanel(panelManager) {
            @Override
            public void armarFormulario() {

                ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
                this.setLayout(new GridLayout(6, 2));
                this.setBackground(Color.ORANGE);

                JPanel matriculaPanel = new JPanel();
                matriculaPanel.setLayout(new BoxLayout(matriculaPanel, BoxLayout.Y_AXIS));
                matriculaPanel.setBackground(Color.CYAN);
                JLabel matriculaLabel = new JLabel("Matricula del medico: ");


                JButton matriculaButton = new JButton("Ver medicos registrados");
                matriculaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelManager.mostrarListaMedicos();
                    }
                });
                matriculaPanel.add(matriculaLabel);
                matriculaPanel.add(opcionesMatriculas = new JComboBox<>(calcularOpcionesMatricula()));
                matriculaPanel.add(matriculaButton);

                JPanel DNIPanel = new JPanel();
                DNIPanel.setLayout(new BoxLayout(DNIPanel, BoxLayout.Y_AXIS));
                DNIPanel.setBackground(Color.PINK);
                JLabel DNILabel = new JLabel("DNI del paciente: ");

                JButton dniButton = new JButton("Ver pacientes registrados");
                dniButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelManager.mostrarListaPacientes();
                    }
                });
                DNIPanel.add(DNILabel);
                DNIPanel.add(opcionesDNI = new JComboBox<>(calcularOpcionesDNI()));
                DNIPanel.add(dniButton);

                JLabel diaLabel = new JLabel("Dia del turno: ");
                Integer[] opcionesDia = new Integer[31];
                for (int i = 0; i < 31; i++) {
                    opcionesDia[i] = i + 1;
                }
                diaOpciones = new JComboBox<>(opcionesDia);

                JLabel mesLabel = new JLabel("Mes del turno: ");
                Integer[] opcionesMes = new Integer[12];
                for (int i = 0; i < 12; i++) {
                    opcionesMes[i] = i + 1;
                }
                mesOpciones = new JComboBox<>(opcionesMes);

                JLabel horaLabel = new JLabel("Hora del turno: ");
                Integer[] opcionesHora = new Integer[24];
                for (int i = 0; i < 24; i++) {
                    opcionesHora[i] = i;
                }
                horaOpciones = new JComboBox<>(opcionesHora);

                String[] opcionesConsultorio = {"Palermo", "Recoleta", "Caballito", "Puerto Madero", "Belgrano" ,"Flores"};
                consultorioOpciones = new JComboBox<>(opcionesConsultorio);


                 JButton refreshButton = new JButton("Refresh");
                 Font font = new Font("Impact", Font.ROMAN_BASELINE ,15);
                 refreshButton.setFont(font);
                 refreshButton.setBackground(Color.gray);
                 refreshButton.setForeground(Color.white);
                refreshButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Integer[] nuevasOpcionesMatricula = calcularOpcionesMatricula();
                        Integer[] nuevasOpcionesDNI = calcularOpcionesDNI();
                        actualizarOpcionesMatricula(nuevasOpcionesMatricula);
                        actualizarOpcionesDNI(nuevasOpcionesDNI);
                        JOptionPane.showMessageDialog(null, "Has actualizado la lista de matriculas y pacientes."
                        );
                    }
                });

                this.add(matriculaPanel);
                this.add(DNIPanel);
                this.add(diaLabel);
                this.add(diaOpciones);
                this.add(mesLabel);
                this.add(mesOpciones);
                this.add(horaLabel);
                this.add(horaOpciones);
                this.add(new JLabel("Consultorio:"));
                this.add(consultorioOpciones);
                this.add(refreshButton);
            }
        };
    }

    @Override
    public void ejecutarAccionOk() {
        try {
            if(opcionesMatriculas.getSelectedItem() == null){
                throw new IllegalArgumentException("No hay medicos para seleccionar. Asegurate de registrar un medico antes de crear un turno.");
            }

            if(opcionesDNI.getSelectedItem() == null){
                throw new IllegalArgumentException("No hay pacientes para seleccionar. Asegurate de registrar un paciente antes de crear un turno.");
            }

            int matricula = Integer.parseInt(opcionesMatriculas.getSelectedItem().toString());
            MedicoService medicoService = new MedicoService();

            if (!medicoService.encuentraMedico(matricula)) {
                throw new IllegalArgumentException("La matricula no representa la de algún medico registrado");
            }

            int DNI = Integer.parseInt(opcionesDNI.getSelectedItem().toString());
            PacienteService pacienteService = new PacienteService();
            if (!pacienteService.encuentraPaciente(DNI)) {
                throw new IllegalArgumentException("El DNI no representa el de un paciente registrado");
            }

            int dia = Integer.parseInt(diaOpciones.getSelectedItem().toString());
            int mes = Integer.parseInt(mesOpciones.getSelectedItem().toString());
            int hora = Integer.parseInt(horaOpciones.getSelectedItem().toString());


            String fecha = dia + "/" + mes;
            int codigoTurno = generarCodigoTurno(matricula, DNI);
            Turno turno = new Turno(codigoTurno, matricula, DNI, fecha, hora, consultorioOpciones.getSelectedItem().toString(),0); // Cambiado consultorioField por consultorioOpciones.getSelectedItem().toString()
            TurnoService turnoService = new TurnoService();
            boolean bool;
            try{
                bool= turnoService.medicoAtiendeEnOtroConsultorio(matricula,fecha,consultorioOpciones.getSelectedItem().toString());
                if(!bool){
                    throw new IllegalArgumentException("El medico atiende en otro consultorio ese dia!");
                }
                bool = turnoService.medicoAtiendeEnEseHorario(matricula,fecha,hora);
                if(!bool){
                    throw new IllegalArgumentException("El medico ya tiene una cita a ese horario!");
                }
                bool = turnoService.pacienteTieneCitaAEseHorario(DNI,fecha,hora);
                if(!bool){
                    throw new IllegalArgumentException("El paciente ya tiene un turno registrado a ese horario.");
                }
            }catch(ServiceException ex){
                JOptionPane.showMessageDialog(null, "Ha habido un error al validar el turno", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            int importe=cobrar(matricula, DNI);
            turno.setImporteTurno(importe);
            turnoService.crearTurno(turno);

            JOptionPane.showMessageDialog(null, "Datos del Turno cargados:\n"
                    + "CodigoTurno: " + turno.getCodigoTurno() + "\n"
                    + "Matricula medico: " + opcionesMatriculas.getSelectedItem().toString() + "\n"
                    + "DNI del paciente: " + opcionesDNI.getSelectedItem().toString() + "\n"
                    + "Horario: " + fecha + " a las " + hora + "hs  \n"
                    + "Consultorio: " + consultorioOpciones.getSelectedItem().toString()
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Los campos: Matricula y DNI deben ser numeros", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, "El turno ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int generarCodigoTurno(int matricula, int DNI) {
        Random random = new Random();
        int codigoTurno = matricula * random.nextInt(100) + DNI * random.nextInt(100);
        return codigoTurno;
    }

    @Override
    public void ejecutarAccionVolver() {
        panelManager.mostrarMenuTurnos();
    }

    public void ejecutarAccionHome() {
        panelManager.mostrarMenuPrincipal();
    }

    public int cobrar(int matricula, int DNI) {
        ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
        PacienteService pacienteService = new PacienteService();
        MedicoService medicoService = new MedicoService();
        try {
            String obraSocialPaciente = pacienteService.retornoObraSocialPaciente(DNI);
            Medico medico = medicoService.muestraMedico(matricula, obrasSociales);
            if (medico.getObraSocial().getNombre_os().equals(obraSocialPaciente)) {
                double valorConsultaF = medico.getPrecioConsulta() * 0.5;
                int valorConsulta = (int) Math.floor(valorConsultaF);
                try {
                    medicoService.actualizaTotalGanado(medico, valorConsulta);
                    return valorConsulta;
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error al actualizar total ganado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    medicoService.actualizaTotalGanado(medico, medico.getPrecioConsulta());
                    return medico.getPrecioConsulta();
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error al actualizar total ganado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    public Integer[] calcularOpcionesMatricula() {
        Integer[] matriculasObj = null;
        try {
            ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
            MedicoService medicoService = new MedicoService();
            int[] matriculas = medicoService.obtenerMatriculasMedicos(obrasSociales);
            matriculasObj = new Integer[matriculas.length];
            for (int i = 0; i < matriculas.length; i++) {
                matriculasObj[i] = matriculas[i];
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return matriculasObj;
    }

    public Integer[] calcularOpcionesDNI() {
        Integer[] DNIObj = null;
        try {
            ObraSocial[] obrasSociales = Utilidades.getObrasSociales();
            PacienteService pacienteService = new PacienteService();
            int[] DNIS = pacienteService.obtenerDNIPacientes(obrasSociales);
            DNIObj = new Integer[DNIS.length];
            for (int i = 0; i < DNIS.length; i++) {
                DNIObj[i] = DNIS[i];
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return DNIObj;
    }

    public void actualizarOpcionesMatricula(Integer[] nuevasOpciones) { //Por si se elimina algun medico
        opcionesMatriculas.removeAllItems();
        for (Integer opcion : nuevasOpciones) {
            opcionesMatriculas.addItem(opcion);
        }
    }

    public void actualizarOpcionesDNI(Integer[] nuevasOpciones) { //Por si se elimina algun paciente
        opcionesDNI.removeAllItems();
        for (Integer opcion : nuevasOpciones) {
            opcionesDNI.addItem(opcion);
        }
    }
}




