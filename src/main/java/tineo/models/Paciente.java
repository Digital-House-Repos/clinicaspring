package tineo.models;

public class Paciente {
    private Integer pacienteID;
    private final String nombre;
    private final String apellido;
    private final String fechaNacimiento;
    private final String telefono;
    private final String email;
    private final Domicilio domicilio;

    public Paciente(String email, Domicilio domicilio, String telefono, String fechaNacimiento, String apellido, String nombre) {
        this.email = email;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Paciente(Integer pacienteID, String nombre, String apellido, String fechaNacimiento, String telefono, String email, Domicilio domicilio) {
        this.pacienteID = pacienteID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.domicilio = domicilio;
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
                ", domicilio=" + domicilio.getDomicilioID() +
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

    public Domicilio getDomicilio() {
        return domicilio;
    }
}
