package TurneraMedica.Pantallas.PantallasMedico;


import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.Medico.Medico;
import TurneraMedica.Medico.MedicoService;
import TurneraMedica.Pantallas.PanelManager;


public class ListaMedicos extends JFrame{
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    protected PanelManager panelManager;
    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void LlenadoDeTablas(List <Medico> listaMedicos,DefaultTableModel tableModel){
        tableModel.addColumn("Matrícula");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Obra Social");
        tableModel.addColumn("Valor de Consulta");
        tableModel.addColumn("Recaudado");

        // Agregar los datos a la tabla
        for (Medico medico : listaMedicos) {
            Object[] rowData = {medico.getMatricula(), medico.getName(), medico.getObraSocial().getNombre_os(), medico.getPrecioConsulta(),medico.getTotalRecaudado()};
            tableModel.addRow(rowData);
        }
    }
    public ListaMedicos(List <Medico> listaMedicos) {

        setTitle("Tabla de Médicos");
        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){ //este metodo sobreescribe el metodo isCellEditable para que no se puedan editar los elementos de la tabla.
                return false;
            }
        };
        LlenadoDeTablas(listaMedicos,tableModel);

        // Crear la tabla
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table); //JScrollPane sirve para que se genera la barra de desplazamiento en caso de que la lista de elementos de la tabla ocupe más espacio que el visible

        // Establezco color de filas y de encabezados
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.CYAN);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(Color.CYAN);
        table.setDefaultRenderer(Object.class, renderer);

        // Creo botón eliminar
        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                MedicoService medicoService= new MedicoService();
                try{
                    medicoService.borrarMedico(selectedRow,tableModel,table);
                }catch(ServiceException ex){
                    JOptionPane.showMessageDialog(null, "Ha habido un error al borrar un medico! (DAO EXCEPTION)", "ERROR", JOptionPane.ERROR_MESSAGE);
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


