package TurneraMedica.Paciente;

import TurneraMedica.ObraSocial.ObraSocial;

public class Paciente {
    private int DNI;
    private String name;
    private ObraSocial obraSocial;
    private String email;

    public String getEmail() {
        return email;
    }
    public int getDNI() {
        return DNI;
    }
    public String getName() {
        return name;
    }
    public String getObraSocial() {return obraSocial.getNombre_os();}

    public void setEmail(String email) {
        email = email;
    }
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Paciente(int DNI, String name,  ObraSocial obraSocial, String email) {
        this.DNI = DNI;
        this.name = name;
        this.obraSocial = obraSocial;
        this.email = email;
    }

}
