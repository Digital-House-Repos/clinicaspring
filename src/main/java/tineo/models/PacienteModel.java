package tineo.models;

public class PacienteModel {
    private Integer pacienteID;
    private final String nombre;
    private final String apellido;
    private final String fechaNacimiento;
    private final String telefono;
    private final String email;
    private final DomicilioModel domicilioModel;

    public PacienteModel(String email, DomicilioModel domicilioModel, String telefono, String fechaNacimiento, String apellido, String nombre) {
        this.email = email;
        this.domicilioModel = domicilioModel;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public PacienteModel(Integer pacienteID, String nombre, String apellido, String fechaNacimiento, String telefono, String email, DomicilioModel domicilioModel) {
        this.pacienteID = pacienteID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.domicilioModel = domicilioModel;
    }

    @Override
    public String toString() {
        return "Paciente [" +
                "pacienteID=" + pacienteID +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", domicilio=" + domicilioModel.getDomicilioID() +
                ']';
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public DomicilioModel getDomicilio() {
        return domicilioModel;
    }
}
