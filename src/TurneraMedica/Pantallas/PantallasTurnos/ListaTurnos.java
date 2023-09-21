package TurneraMedica.Pantallas.PantallasTurnos;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Pantallas.PanelManager;
import TurneraMedica.Turnos.Turno;
import TurneraMedica.Turnos.TurnoService;


public class ListaTurnos extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    protected PanelManager panelManager;
    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void LlenadoDeTablas(List <Turno> listaTurnos,DefaultTableModel tableModel){

        tableModel.addColumn("Codigo Turno");
        tableModel.addColumn("Matrícula");
        tableModel.addColumn("DNI");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Hora");
        tableModel.addColumn("Consultorio");

        // Agregar los datos a la tabla
        for (Turno turno : listaTurnos) {
            Object[] rowData = { turno.getCodigoTurno(), turno.getMatricula(), turno.getDNI(), turno.getFecha(), turno.getHora(),turno.getConsultorio()};
            tableModel.addRow(rowData);
        }
    }

    public ListaTurnos(List <Turno> listaTurnos) {

        setTitle("Tabla de Médicos");
        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){ //este metodo sobreescribe el metodo isCellEditable para que no se puedan editar los elementos de la tabla.
                return false;
            }
        };
        LlenadoDeTablas(listaTurnos,tableModel);

        // Crear la tabla
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table); //JScrollPane sirve para que se genera la barra de desplazamiento en caso de que la lista de elementos de la tabla ocupe más espacio que el visible

        // Establezco color de filas y de encabezados
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.ORANGE);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(Color.ORANGE);
        table.setDefaultRenderer(Object.class, renderer);

        // Creo botón eliminar
        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                TurnoService turnoService= new TurnoService();
                try{
                    turnoService.borrarTurno(selectedRow,tableModel,table);
                }catch(ServiceException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error al borrar los turnos! (DAO EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        // Creo el panel para el boton
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));
        buttonPanel.add(deleteButton);

        // Creo el contenedor principal y agregar los componentes
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }
}
