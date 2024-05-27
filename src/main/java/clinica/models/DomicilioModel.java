package clinica.models;

public class DomicilioModel {
    private Integer domicilioID;
    private final String calle;
    private final Integer numero;
    private final String localidad;
    private final String provincia;

    public DomicilioModel(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioModel(Integer domicilioID, String calle, Integer numero, String localidad, String provincia) {
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

    public void setDomicilioID(Integer domicilioID) {
        this.domicilioID = domicilioID;
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
