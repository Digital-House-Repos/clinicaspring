package tineo;

import org.junit.Assert;
import org.junit.Test;
import tineo.dao.DBInitializer;

public class InsertDataTest {
    @Test
    public void insertDataPacienteTest() {
        Assert.assertEquals("200", DBInitializer.insertDataPaciente());
    }
}
