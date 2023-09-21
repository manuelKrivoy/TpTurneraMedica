package TurneraMedica.Medico;

import TurneraMedica.DBManager;
import TurneraMedica.Utilidades;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManagerMedico {
    public void createMedicoTable() {
        Connection c = DBManager.connect();
        String sql = "CREATE TABLE IF NOT EXISTS Medicos ( Matricula INTEGER IDENTITY, name VARCHAR(256), obraSocial VARCHAR(256), valorConsulta INTEGER, totalGanado INTEGER )";
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
        String sql = "DROP TABLE Medicos";
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

