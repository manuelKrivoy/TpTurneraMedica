package TurneraMedica.Turnos;

import TurneraMedica.DBManager;
import TurneraMedica.Utilidades;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManagerTurno {
    public void createTurnoTable() {
        Connection c = DBManager.connect();
        String sql = "CREATE TABLE IF NOT EXISTS Turnos (CodigoTurno INTEGER IDENTITY, Matricula INTEGER , DNI INTEGER, Fecha VARCHAR(256), Hora INTEGER, Consultorio VARCHAR(256),ImporteTurno INTEGER )";
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
        String sql = "DROP TABLE Turnos";
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
