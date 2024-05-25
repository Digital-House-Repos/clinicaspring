package tineo;

import org.junit.Assert;
import org.junit.Test;
import tineo.dao.DBInitializer;

public class CreateTableTest {
    @Test
    public void createTableDomicilioTest() {
        Assert.assertEquals("200", DBInitializer.createTableDomicilio());
    }

    @Test
    public void createTablePacienteTest() {
        Assert.assertEquals("200", DBInitializer.createTablePaciente());
    }
}