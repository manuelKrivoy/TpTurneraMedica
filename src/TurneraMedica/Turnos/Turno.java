package TurneraMedica.Turnos;

public class Turno {

    private int codigoTurno;
    private int matricula;
    private int DNI;
    private String fecha;
    private int hora;
    private String consultorio;

    private int importeTurno;


    public Turno(int codigoTurno,int matricula, int DNI, String fecha, int hora, String consultorio,int importeTurno) {
        this.codigoTurno= codigoTurno;
        this.matricula = matricula;
        this.DNI = DNI;
        this.fecha = fecha;
        this.hora = hora;
        this.consultorio = consultorio;
        this.importeTurno = importeTurno;
    }

    public int getCodigoTurno() {
        return codigoTurno;
    }

    public int getMatricula() {
        return matricula;
    }

    public int getDNI() {
        return DNI;
    }

    public void setImporteTurno(int importeTurno) {
        this.importeTurno = importeTurno;
    }

    public String getFecha() {
        return fecha;
    }
    public int getHora() {return hora;}
    public String getConsultorio() {
        return consultorio;
    }
    public int getImporteTurno() {return importeTurno;}
}
