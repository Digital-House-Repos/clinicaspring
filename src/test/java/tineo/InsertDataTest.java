package tineo;

import org.junit.Assert;
import org.junit.Test;
import tineo.dao.DBInitializer;
import tineo.models.DomicilioModel;
import tineo.models.PacienteModel;

public class InsertDataTest {
    @Test
    public void insertDataPacienteTest() {
        DomicilioModel domicilio = new DomicilioModel("Calle Falsa", 123, "Springfield", "Springfield");
        PacienteModel paciente = new PacienteModel("Homero", "Simpson", "12345678", "2021-01-01", domicilio);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(domicilio, paciente));

        DomicilioModel d2 = new DomicilioModel("Calle Falsa 2", 324, "Austria", "Viena");
        PacienteModel p2 = new PacienteModel("Bart", "Simpson", "87654321", "2021-01-01", d2);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(d2, p2));

        DomicilioModel d3 = new DomicilioModel("Calle Falsa 3", 345, "Italia", "Roma");
        PacienteModel p3 = new PacienteModel("Lisa", "Simpson", "12348765", "2021-01-01", d3);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(d3, p3));

        DomicilioModel d4 = new DomicilioModel("Calle Falsa 4", 456, "Francia", "Paris");
        PacienteModel p4 = new PacienteModel("Marge", "Simpson", "87651234", "2021-01-01", d4);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(d4, p4));
    }
}
