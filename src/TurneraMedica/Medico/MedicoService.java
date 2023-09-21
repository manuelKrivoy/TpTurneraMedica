package TurneraMedica.Medico;
import java.sql.SQLException;
import java.util.List;

import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.Exeptions.ServiceException;
import TurneraMedica.ObraSocial.ObraSocial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MedicoService {
    public void crearMedico(Medico medico) throws ServiceException {
        MedicoDAO dao = new MedicoH2Impl();
        try {
            dao.crearMedico(medico);
        } catch (ObjectoDuplicadoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Medico> listaTodosLosMedicos(ObraSocial[] obrasSociales) throws ServiceException{
        MedicoDAO dao = new MedicoH2Impl();
        try{
        List<Medico> medicos = dao.listaTodosLosMedicos(obrasSociales);
        return medicos;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void borrarMedico(int selectedRow, DefaultTableModel tableModel, JTable table) throws ServiceException{
        MedicoDAO dao = new MedicoH2Impl();
        if (selectedRow >= 0 && selectedRow < tableModel.getRowCount()) { //este if se implementa para que el indice se mueva bien. sino habia problemas para borrar medicos una vez que quedaban 3
            int matricula = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            try {
                dao.borrarMedico(matricula);
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

    public boolean encuentraMedico(int matricula) throws  ServiceException{
        MedicoDAO dao = new MedicoH2Impl();
        try{
            return dao.encuentraMedico(matricula);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Medico muestraMedico(int matricula, ObraSocial[] obrasSociales )  throws ServiceException{
        MedicoDAO dao = new MedicoH2Impl();
        try{
            return dao.muestraMedico(matricula,obrasSociales);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void actualizaTotalGanado(Medico medico, int valorConsulta) throws ServiceException{
        MedicoDAO dao = new MedicoH2Impl();
        try{
            dao.actualizaTotalGanado(medico,valorConsulta);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int[] obtenerMatriculasMedicos(ObraSocial[] obrasSociales) throws ServiceException{
        MedicoDAO dao = new MedicoH2Impl();
        try{
            return dao.obtenerMatriculasMedicos(obrasSociales);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
