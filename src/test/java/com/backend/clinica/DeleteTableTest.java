package com.backend.clinica;

import org.junit.Assert;
import org.junit.Test;
import com.backend.clinica.repository.DBInitializer;

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