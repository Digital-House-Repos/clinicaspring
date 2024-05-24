package tineo.models;

public class Domicilio {
    private Integer domicilioID;
    private final String calle;
    private final Integer numero;
    private final String localidad;
    private final String provincia;

    public Domicilio(String localidad, String provincia, Integer numero, String calle) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.numero = numero;
        this.calle = calle;
    }

    public Domicilio(Integer domicilioID, String calle, Integer numero, String localidad, String provincia) {
        this.domicilioID = domicilioID;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Domicilio [" +
                "domicilioID=" + domicilioID +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ']';
    }

    public Integer getDomicilioID() {
        return domicilioID;
    }

    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }
}
