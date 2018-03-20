package ru.ezhov.template.core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WithoutDashNameTest {
    @Test
    public void getOk() throws Exception {
        Name name = new WithoutDashName(() -> "fac5071d-e26d-41ba-aeb6-81ad9035382c");
        String n = name.get();

        assertTrue(n.length() == 32);
    }

}