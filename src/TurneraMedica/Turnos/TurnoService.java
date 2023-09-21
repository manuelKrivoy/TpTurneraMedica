package TurneraMedica.Turnos;

import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.ObraSocial.ObraSocial;
import TurneraMedica.Paciente.Paciente;
import TurneraMedica.Paciente.PacienteDAO;
import TurneraMedica.Paciente.PacienteH2Impl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
public class TurnoService {

    public void crearTurno(Turno turno) throws ServiceException {
        TurnoDAO dao = new TurnoH2Impl();
        try {
            dao.crearTurno(turno);
        } catch (ObjectoDuplicadoException e) {
            throw new ServiceException(e);
        }
    }

    public void borrarTurno(int selectedRow, DefaultTableModel tableModel, JTable table) throws ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        if (selectedRow >= 0 && selectedRow < tableModel.getRowCount()) { //este if se implementa para que el indice se mueva bien. sino habia problemas para borrar medicos una vez que quedaban 3
            int codigoTurno = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()); //saco valor de la columna 0 de la tabla (pk)
            try {
                dao.borrarTurno(codigoTurno);
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

    public List<Turno> listaTodosLosTurnos() throws ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        try{
            List<Turno> turnos = dao.listaTodosLosTurnos();
            return turnos;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean medicoAtiendeEnOtroConsultorio(int matricula, String fecha, String consultorio) throws  ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        Boolean bool = null;
        try{
           bool = dao.medicoAtiendeEnOtroConsultorio(matricula,fecha,consultorio);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bool;
    }

    public boolean medicoAtiendeEnEseHorario(int matricula,String fecha,int hora) throws  ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        Boolean bool = null;
        try{
            bool = dao.medicoAtiendeEnEseHorario(matricula,fecha,hora);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bool;
    }
    public boolean pacienteTieneCitaAEseHorario(int DNI,String fecha,int hora) throws  ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        Boolean bool = null;
        try{
            bool = dao.pacienteTieneCitaAEseHorario(DNI,fecha,hora);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bool;
    }

    public List<Turno> listaCuandoTengoTurnoPaciente(int DNI) throws ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        try{
            List<Turno> turnos = dao.listaCuandoTengoTurnoPaciente(DNI);
            return turnos;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Turno> listaCuandoTengoTurnoMedico(int matricula) throws ServiceException{
        TurnoDAO dao = new TurnoH2Impl();
        try{
            List<Turno> turnos = dao.listaCuandoTengoTurnoMedico(matricula);
            return turnos;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
