package clinica;

import org.junit.Assert;
import org.junit.Test;
import clinica.dao.DBInitializer;

public class DeleteTableTest {
    @Test
    public void deleteTableDomicilioTest() {
        Assert.assertEquals("200", DBInitializer.deleteTableDomicilio());
    }

    @Test
    public void deleteTablePacienteTest() {
        Assert.assertEquals("200", DBInitializer.deleteTablePaciente());
    }
}