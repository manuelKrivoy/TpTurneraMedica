package TurneraMedica.Turnos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.DBManager;
import TurneraMedica.Utilidades;
public class TurnoH2Impl implements TurnoDAO {
    public void crearTurno(Turno turno) throws  ObjectoDuplicadoException {
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String sql = "INSERT INTO Turnos (CodigoTurno, Matricula , DNI, Fecha, Hora, Consultorio,ImporteTurno) VALUES ('" + turno.getCodigoTurno() + "', '"+ turno.getMatricula() +"', '" + turno.getDNI() + "', '" + turno.getFecha() + "', '" +turno.getHora() + "', '" + turno.getConsultorio()+ "', '" +turno.getImporteTurno() + "')";
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
    public void borrarTurno(int codigoTurno) throws DAOException{
        String sql = "DELETE FROM Turnos WHERE CodigoTurno = '" + codigoTurno + "'";
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

    public void actualizaTurno(Turno turno){
        String sql = "UPDATE Turnos set Hora = '" + turno.getHora() + "', Fecha = '" + turno.getFecha() + "', Matricula= '" + turno.getMatricula() +"', DNI= '" + turno.getDNI() + "', Consultorio='" + turno.getConsultorio() + "\" WHERE CodigoTurno = \"" + turno.getCodigoTurno() + "'";
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
   public Turno muestraTurno(int codigoTurno) throws SQLException{
       String sql = "SELECT * FROM Turnos WHERE CodigoTurno = '" + codigoTurno + "'";
       Connection c = DBManager.connect();
       try {
           Statement s = c.createStatement();
           ResultSet rs = s.executeQuery(sql);
           if (rs.next()) {
               return new Turno(rs.getInt("CodigoTurno"), rs.getInt("Matricula"), rs.getInt("DNI") ,rs.getString("Fecha"),rs.getInt("Hora"), rs.getString("Consultorio"),rs.getInt("ImporteTurno"));
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

    public List<Turno> listaTodosLosTurnos () throws SQLException{
        List<Turno> listaTurnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                Turno u = new Turno(rs.getInt("CodigoTurno"), rs.getInt("Matricula"), rs.getInt("DNI") ,rs.getString("Fecha"),rs.getInt("Hora"), rs.getString("Consultorio"),rs.getInt("ImporteTurno"));
                listaTurnos.add(u);

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
        return listaTurnos;
    }

    public boolean medicoAtiendeEnOtroConsultorio(int matricula,String fecha, String consultorio) throws  SQLException{
        String sql = "SELECT * FROM Turnos";
        Connection c = DBManager.connect();
        Boolean bool = true;
        try{
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                if(matricula == rs.getInt("Matricula") && rs.getString("Fecha").equals(fecha)  && !(rs.getString("Consultorio").equals(consultorio))  ) bool= false;
            }

        }catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        return bool;
    }

    public boolean medicoAtiendeEnEseHorario(int matricula,String fecha, int hora) throws  SQLException{
        String sql = "SELECT * FROM Turnos";
        Connection c = DBManager.connect();
        Boolean bool = true;
        try{
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                if( rs.getInt("Matricula") == matricula && rs.getString("Fecha").equals(fecha)  && rs.getInt("Hora") == hora  ) bool = false;
            }

        }catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        return bool;
    }

    public boolean pacienteTieneCitaAEseHorario(int DNI,String fecha, int hora) throws  SQLException{
        String sql = "SELECT * FROM Turnos";
        Connection c = DBManager.connect();
        Boolean bool= true;
        try{
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                if( rs.getInt("DNI") == DNI && rs.getString("Fecha").equals(fecha)  && rs.getInt("Hora") == hora  ) bool = false;
            }

        }catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
        return bool;

    }

    public List<Turno> listaCuandoTengoTurnoMedico (int matricula) throws SQLException{
        List<Turno> listaTurnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos where matricula = " + matricula;
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                Turno u = new Turno(rs.getInt("CodigoTurno"), rs.getInt("Matricula"), rs.getInt("DNI") ,rs.getString("Fecha"),rs.getInt("Hora"), rs.getString("Consultorio"),rs.getInt("ImporteTurno"));
                listaTurnos.add(u);

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
        return listaTurnos;
    }

    public List<Turno> listaCuandoTengoTurnoPaciente (int DNI) throws SQLException{
        List<Turno> listaTurnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos where DNI = " + DNI;
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                Turno u = new Turno(rs.getInt("CodigoTurno"), rs.getInt("Matricula"), rs.getInt("DNI") ,rs.getString("Fecha"),rs.getInt("Hora"), rs.getString("Consultorio"),rs.getInt("ImporteTurno"));
                listaTurnos.add(u);

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
        return listaTurnos;
    }

}
