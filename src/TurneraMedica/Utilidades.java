package TurneraMedica;

import TurneraMedica.ObraSocial.ObraSocial;
import TurneraMedica.Turnos.Turno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Utilidades  {

    public static ObraSocial convertirEnObraSocial(String obraSocial){  //Metodo que valida que las obras sociales ingresadas sean validas.
        ObraSocial[] obrasSociales = getObrasSociales();
        ObraSocial obraSocialDeRetorno = null;
        for (int i = 0; i < obrasSociales.length; i++) {
            if(obrasSociales[i].getNombre_os().equals(obraSocial.toUpperCase())) { // se pasa variable a mayusculas asi coincide con las posibles obras sociales (mejora experiencia usuario de no tener que copiarlo tal cual)
                obraSocialDeRetorno= new ObraSocial(obrasSociales[i].getCod_os(),obrasSociales[i].getNombre_os());
                break;
            }
        }
        return obraSocialDeRetorno;
    }


    public static ObraSocial[] getObrasSociales() { //Metodo que genera las obras sociales que soporta el sistema.
        ObraSocial[] obrasSociales = new ObraSocial[8];
        obrasSociales[0] = new ObraSocial("SWM", "SWISS MEDICAL");
        obrasSociales[1] = new ObraSocial("OSD", "OSDE");
        obrasSociales[2] = new ObraSocial("AVL", "AVALIAN");
        obrasSociales[3] = new ObraSocial("PVR", "PREVENCIÓN SALUD");
        obrasSociales[4] = new ObraSocial("GLN", "GALENO");
        obrasSociales[5] = new ObraSocial("SCS", "SANCOR SALUD");
        obrasSociales[6] = new ObraSocial("MDC", "MEDICUS");
        obrasSociales[7] = new ObraSocial("OMN", "OMINT");
        return obrasSociales;
    }

    public static ObraSocial stringToObraSocial(ResultSet rs,ObraSocial obrasSociales[]) throws  SQLException{ // Metodo que a partir de un String que sea el nombre de una obra social retorne un objeto de tipo ObraSocial
        String obra = rs.getString("obraSocial");
        ObraSocial os= null;
        for (int i = 0; i < obrasSociales.length; i++) {
            if(obrasSociales[i].getNombre_os().equals(obra)) {
                os = new ObraSocial(obrasSociales[i].getCod_os(),obra);
                break;
            }
        }
        return os;
    }
    public static void h2ImplFinally(Connection c){  //Metodo para cerrar la base de datos.
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
    }

    public static void mostrarPantalla(JFrame frame, JPanel panel) { //Hace el cambio de pantalla en el PanelManager.
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public static int extraerMes(String date) { //Teniendo una fecha String devuelve el mes en int.
        StringTokenizer tokenizer = new StringTokenizer(date, "/");
        tokenizer.nextToken(); // Saltea el dia
        String mesString = tokenizer.nextToken();
        int mes = Integer.parseInt(mesString);
        return mes;
    }

    public static int extraerDia(String date) { //Teniendo una fecha String devuelve el mes en int.
        StringTokenizer tokenizer = new StringTokenizer(date, "/");
        String diaString = tokenizer.nextToken();
        int dia = Integer.parseInt(diaString);
        return dia;
    }
    public static List<Turno> extraerTurnosFecha(List<Turno> turnos, String fecha) { //devuelve lista de turnos de esa fecha
        List<Turno> listaDeTurnosEnFecha = new ArrayList<>();
        for (Turno turno : turnos) {
            if (fecha.equals(turno.getFecha())) {
                listaDeTurnosEnFecha.add(turno);
            }
        }
        return listaDeTurnosEnFecha;
    }

}