package TurneraMedica.Paciente;


import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.ObraSocial.ObraSocial;

import java.sql.SQLException;
import java.util.List;

public interface PacienteDAO {

    void crearPaciente(Paciente Paciente) throws ObjectoDuplicadoException;

    void borrarPaciente(int DNI) throws DAOException;

    boolean encuentraPaciente(int DNI ) throws SQLException;

    Paciente muestraPaciente(int DNI, ObraSocial obrasSociales[]) throws SQLException;

    String retornoObraSocialPaciente(int DNI) throws  SQLException;
    int[] obtenerDNIPacientes(ObraSocial[] obrasSociales) throws SQLException;

    List<Paciente> listaTodosLosPacientes( ObraSocial obrasSociales[] ) throws SQLException;

}
