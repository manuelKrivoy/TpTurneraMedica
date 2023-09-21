package TurneraMedica.Medico;

import TurneraMedica.Exeptions.DAOException;
import TurneraMedica.Exeptions.ObjectoDuplicadoException;
import TurneraMedica.ObraSocial.ObraSocial;

import java.sql.SQLException;
import java.util.List;

public interface MedicoDAO {

    void crearMedico(Medico Medico) throws ObjectoDuplicadoException;

    void borrarMedico(int matricula) throws DAOException;


    void actualizaTotalGanado(Medico medico, int valorConsulta) throws SQLException;

    Medico muestraMedico(int matricula, ObraSocial obrasSociales[] ) throws SQLException;
    boolean encuentraMedico(int matricula) throws  SQLException;

    String retornoObraSocialMedico(int matricula) throws SQLException;

    int[] obtenerMatriculasMedicos(ObraSocial[] obrasSociales) throws SQLException;

    List<Medico> listaTodosLosMedicos(ObraSocial obrasSociales[] ) throws SQLException;
}
