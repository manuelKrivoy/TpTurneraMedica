package TurneraMedica.Paciente;

import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.ObraSocial.ObraSocial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class PacienteService {
    public void crearPaciente(Paciente paciente) throws ServiceException {
        PacienteDAO dao = new PacienteH2Impl();
        try {
            dao.crearPaciente(paciente);
        } catch (ObjectoDuplicadoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean encuentraPaciente(int DNI) throws ServiceException {
        PacienteDAO dao= new PacienteH2Impl();
        try{
            return dao.encuentraPaciente(DNI);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Paciente> listaTodosLosPacientes(ObraSocial[] obrasSociales) throws ServiceException{
        PacienteDAO dao = new PacienteH2Impl();
        try{
            List<Paciente> pacientes = dao.listaTodosLosPacientes(obrasSociales);
            return pacientes;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String retornoObraSocialPaciente(int DNI) throws ServiceException {
        PacienteDAO dao= new PacienteH2Impl();
        try{
            return dao.retornoObraSocialPaciente(DNI);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void borrarPaciente(int selectedRow, DefaultTableModel tableModel, JTable table) throws ServiceException{
        PacienteDAO dao = new PacienteH2Impl();
        if (selectedRow >= 0 && selectedRow < tableModel.getRowCount()) { //este if se implementa para que el indice se mueva bien. sino habia problemas para borrar medicos una vez que quedaban 3
            int dni = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()); //saco valor de la columna 0 de la tabla (pk)
            try {
                dao.borrarPaciente(dni);
                tableModel.removeRow(selectedRow);
                if (tableModel.getRowCount() > 0) {
                    int nextRow = Math.min(selectedRow, tableModel.getRowCount() - 1);
                    table.setRowSelectionInterval(nextRow, nextRow);
                }
            } catch (DAOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int[] obtenerDNIPacientes(ObraSocial[] obrasSociales) throws ServiceException{
        PacienteDAO dao = new PacienteH2Impl();
        try{
            return dao.obtenerDNIPacientes(obrasSociales);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
