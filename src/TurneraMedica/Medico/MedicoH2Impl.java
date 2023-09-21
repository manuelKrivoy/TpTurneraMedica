package TurneraMedica.Medico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import TurneraMedica.Exeptions.DAOException;

import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.DBManager;
import TurneraMedica.ObraSocial.ObraSocial;
import TurneraMedica.Utilidades;

public class MedicoH2Impl implements MedicoDAO {

    public void crearMedico(Medico medico) throws ObjectoDuplicadoException {
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String sql = "INSERT INTO Medicos (Matricula,name,obraSocial,valorConsulta,totalGanado) VALUES ('" + medico.getMatricula() + "', '"+ medico.getName() +"', '" + medico.getObraSocial().getNombre_os() + "', '" + medico.getPrecioConsulta() + "', '" + 0 + "')";
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new ObjectoDuplicadoException();
                }
                e.printStackTrace();
                c.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
    }

    public void borrarMedico(int matricula) throws DAOException {
        String sql = "DELETE FROM Medicos WHERE Matricula = '" + matricula + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DAOException();
        } finally {
            Utilidades.h2ImplFinally(c);
        }
    }

    public void actualizaTotalGanado(Medico medico,int valorConsulta) throws SQLException{
        String sql = "UPDATE Medicos SET totalGanado = " + (medico.getTotalRecaudado() + valorConsulta) + " WHERE MATRICULA = " + medico.getMatricula();
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
    }

    public Medico muestraMedico(int matricula, ObraSocial obrasSociales[]) throws SQLException {
        String sql = "SELECT * FROM Medicos WHERE Matricula = '" + matricula + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                ObraSocial os=Utilidades.stringToObraSocial(rs,obrasSociales);
                return new Medico(rs.getInt("Matricula"), rs.getString("name"), os ,rs.getInt("valorConsulta"),rs.getInt("totalGanado"));

            }
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        return null;
    }

    public List<Medico> listaTodosLosMedicos( ObraSocial obrasSociales[]) throws SQLException {
        List<Medico> listaMedicos = new ArrayList<>();
        String sql = "SELECT * FROM Medicos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                ObraSocial os=Utilidades.stringToObraSocial(rs,obrasSociales);
                Medico med = new Medico(rs.getInt("Matricula"), rs.getString("name"), os ,rs.getInt("valorConsulta"),rs.getInt("totalGanado"));
                listaMedicos.add(med);
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }

        return listaMedicos;
    }

    public int[] obtenerMatriculasMedicos(ObraSocial[] obrasSociales) throws SQLException {
        String sql = "SELECT Matricula FROM Medicos";
        Connection c = DBManager.connect();
        List<Integer> listaMatriculas = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                listaMatriculas.add(rs.getInt("Matricula"));
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        // de lista a array
        int[] matriculasArray = new int[listaMatriculas.size()];
        for (int i = 0; i < listaMatriculas.size(); i++) {
            matriculasArray[i] = listaMatriculas.get(i);
        }
        return matriculasArray;
    }

    public boolean encuentraMedico(int matricula) throws SQLException{
        String sql = "SELECT * FROM Medicos WHERE Matricula = '" + matricula + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        return false;
    }

    public String retornoObraSocialMedico(int matricula) throws  SQLException{
        String sql = "SELECT * FROM Medicos WHERE Matricula = '" + matricula + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("obraSocial");
            }
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        return null;
    }
}


