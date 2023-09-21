package TurneraMedica.Pantallas.PantallasPaciente;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Pantallas.PanelManager;

import TurneraMedica.Paciente.Paciente;
import TurneraMedica.Paciente.PacienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.List;

public class ListaPacientes extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    protected PanelManager panelManager;

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void LlenadoDeTablas(List<Paciente> listaPacientes, DefaultTableModel tableModel) {
        tableModel.addColumn("DNI");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Obra Social");
        tableModel.addColumn("Email");

        // Agregar los datos a la tabla
        for (Paciente paciente : listaPacientes) {
            Object[] rowData = {paciente.getDNI(), paciente.getName(), paciente.getObraSocial(), paciente.getEmail()};
            tableModel.addRow(rowData);
        }
    }

    public ListaPacientes(List<Paciente> listaPacientes) {
        setTitle("Tabla de Pacientes");

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        LlenadoDeTablas(listaPacientes, tableModel);

        // Crear la tabla
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Establezco color de filas y de encabezados
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.PINK);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(Color.PINK);
        table.setDefaultRenderer(Object.class, renderer);

        // Creao botón eliminar
        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            PacienteService pacienteService = new PacienteService();
            try {
                pacienteService.borrarPaciente(selectedRow, tableModel, table);
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null, "Ha habido un error a borrar el paciente! (DAO EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Creo el panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.add(deleteButton);

        // Creao el contenedor principal y agregar los componentes
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }
}

