package TurneraMedica;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class DBManager {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost//{DIR}";
    private static final String DB_NAME = "/TurneraMedica";
    private static final String DB_USERNAME = "Manuel";
    private static final String DB_PASSWORD = "";

    public static Connection connect() {
        Connection c = null;
        try { //para ejecutar driver
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) { //por si escribi mal la url. en vez de org.h2.driver podria haber puesto org.h2.driber
            e.printStackTrace();
            System.exit(0); //mata el programa.
        }
        try { //para conectarnos a la bdd
            String url = "jdbc:h2:tcp://localhost//{DIR}/ejemplo";
            url = url.replace("{DIR}", getDirectorioBase()); //este llama al metodo para ubicar la url creada para la bdd
            System.out.println(url);
            c = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD); //Llama metodo de h2 para conectar a la bdd
            c.setAutoCommit(false); //las transacciones se manejan manual, si fuese true serian automaticas.
        } catch (SQLException e) { //todo tira sqlException, que hay que saber manejar.
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONECTAR CON LA BDD", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }

    private static String getDirectorioBase() {
        File currDir = new File("bdd/");
        return currDir.getAbsolutePath();
    }

    public static String obtenerUbicacionBase() {
        String url = "jdbc:h2:tcp://localhost//{DIR}/ejemplo";
        url = url.replace("{DIR}", getDirectorioBase());
        return url;
    }

}
