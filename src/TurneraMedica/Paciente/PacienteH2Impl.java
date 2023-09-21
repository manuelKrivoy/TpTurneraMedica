package TurneraMedica.Paciente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.DBManager;
import TurneraMedica.ObraSocial.ObraSocial;
import TurneraMedica.Utilidades;

public class PacienteH2Impl implements PacienteDAO {

    public void crearPaciente(Paciente paciente) throws ObjectoDuplicadoException {

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String sql = "INSERT INTO Pacientes (DNI, name, email, obraSocial) VALUES ('" + paciente.getDNI() + "', '"+ paciente.getName() +"', '" + paciente.getEmail() + "', '" + paciente.getObraSocial() + "')";
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

    public void borrarPaciente(int DNI) throws DAOException {
        String sql = "DELETE FROM Pacientes WHERE DNI = '" + DNI + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            throw new DAOException();
        } finally {
            Utilidades.h2ImplFinally(c);
        }
    }

    public boolean encuentraPaciente(int DNI) throws SQLException {
        String sql = "SELECT * FROM Pacientes WHERE DNI = '" + DNI + "'";
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

    public List<Paciente> listaTodosLosPacientes( ObraSocial obrasSociales[]) throws SQLException {
        List<Paciente> listaPacientes = new ArrayList<>();
        String sql = "SELECT * FROM Pacientes";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                ObraSocial os=Utilidades.stringToObraSocial(rs,obrasSociales);
                Paciente u = new Paciente(rs.getInt("DNI"), rs.getString("name"), os ,rs.getString("email"));
                listaPacientes.add(u);

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
        return listaPacientes;
    }
    public Paciente muestraPaciente(int DNI, ObraSocial obrasSociales[]) throws SQLException {
        String sql = "SELECT * FROM Pacientes WHERE DNI = '" + DNI + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                ObraSocial os=Utilidades.stringToObraSocial(rs,obrasSociales);
                return  new Paciente(rs.getInt("DNI"), rs.getString("name"), os ,rs.getString("Email"));
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

    public String retornoObraSocialPaciente(int DNI) throws  SQLException{
        String sql = "SELECT * FROM Pacientes WHERE DNI = '" + DNI + "'";
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

    public int[] obtenerDNIPacientes(ObraSocial[] obrasSociales) throws SQLException {
        String sql = "SELECT DNI FROM Pacientes";
        Connection c = DBManager.connect();
        List<Integer> listaDNI = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                listaDNI.add(rs.getInt("DNI"));
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
        int[] DNIArray = new int[listaDNI.size()];
        for (int i = 0; i < listaDNI.size(); i++) {
            DNIArray[i] = listaDNI.get(i);
        }
        return DNIArray;
    }



}
