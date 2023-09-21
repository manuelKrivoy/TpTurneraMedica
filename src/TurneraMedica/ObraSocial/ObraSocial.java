package TurneraMedica.ObraSocial;

public class ObraSocial {
    private String cod_os;
    private String nombre_os;

    public ObraSocial(String cod_os, String nombre_os) {
        this.cod_os = cod_os;
        this.nombre_os = nombre_os;
    }

    public String getCod_os() {
        return cod_os;
    }
    public String getNombre_os() {
        return nombre_os;
    }
}
