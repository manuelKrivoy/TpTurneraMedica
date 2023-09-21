package TurneraMedica.Medico;

import TurneraMedica.ObraSocial.ObraSocial;

public class Medico  {
     private int matricula;
     private String name;
     private ObraSocial obraSocial;
     private int precioConsulta;

     private int totalRecaudado=0;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public int getPrecioConsulta() {
        return precioConsulta;
    }

    public void setPrecioConsulta(int precioConsulta) {
        this.precioConsulta = precioConsulta;
    }

    public int getTotalRecaudado() {return totalRecaudado;}

    public void setTotalRecaudado(int totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    public Medico(int matricula, String name, ObraSocial obraSocial, int precioConsulta, int totalRecaudado) {
        this.matricula = matricula;
        this.name = name;
        this.obraSocial = obraSocial;
        this.precioConsulta = precioConsulta;
        this.totalRecaudado= totalRecaudado;
    }
}
