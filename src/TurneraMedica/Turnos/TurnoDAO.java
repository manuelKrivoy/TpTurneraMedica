package TurneraMedica.Turnos;

import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.Exeptions.ObjectoDuplicadoException;

import java.sql.SQLException;
import java.util.List;

public interface TurnoDAO {

    void crearTurno(Turno turno) throws  ObjectoDuplicadoException;

    void borrarTurno(int codigoTurno) throws DAOException;

    void actualizaTurno(Turno turno);

    Turno muestraTurno(int codigoTurno) throws SQLException;

    List<Turno> listaTodosLosTurnos () throws SQLException;

    List<Turno> listaCuandoTengoTurnoMedico(int matricula) throws  SQLException;

    List<Turno> listaCuandoTengoTurnoPaciente(int DNI) throws  SQLException;

    boolean medicoAtiendeEnOtroConsultorio(int matricula,String fecha, String consultorio) throws  SQLException;

    boolean medicoAtiendeEnEseHorario(int matricula,String fecha, int hora) throws  SQLException;

    boolean pacienteTieneCitaAEseHorario(int DNI,String fecha, int hora) throws  SQLException;



}
