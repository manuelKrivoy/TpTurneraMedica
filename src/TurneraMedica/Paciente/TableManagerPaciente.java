package TurneraMedica.Paciente;

import TurneraMedica.DBManager;
import TurneraMedica.Utilidades;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManagerPaciente {
    public void createPacienteTable() {
        Connection c = DBManager.connect();
        String sql = "CREATE TABLE IF NOT EXISTS Pacientes ( DNI INTEGER IDENTITY, name VARCHAR(256), email VARCHAR(256), obraSocial VARCHAR(256) )";
        try {
            Statement s = c.createStatement();
            s.execute(sql);
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

    public void dropTable() {
        Connection c = DBManager.connect();
        String sql = "DROP TABLE Pacientes";
        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Utilidades.h2ImplFinally(c);
        }
    }
}
