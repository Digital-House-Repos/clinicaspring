package tineo.models;

public class PacienteModel {
    private Integer pacienteID;
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final String fechaIngreso;
    private final DomicilioModel domicilioID;

    public PacienteModel(String nombre, String apellido, String dni, String fechaIngreso, DomicilioModel domicilioID) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioID = domicilioID;
    }

    public PacienteModel(String fechaIngreso, DomicilioModel domicilioID, String dni, String apellido, String nombre, Integer pacienteID) {
        this.fechaIngreso = fechaIngreso;
        this.domicilioID = domicilioID;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.pacienteID = pacienteID;
    }

    @Override
    public String toString() {
        return "Paciente [" +
                "pacienteID=" + pacienteID +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                ", domicilioID=" + domicilioID +
                ']';
    }

    public void setPacienteID(Integer pacienteID) {
        this.pacienteID = pacienteID;
    }

    public Integer getPacienteID() {
        return pacienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public DomicilioModel getDomicilioID() {
        return domicilioID;
    }
}
