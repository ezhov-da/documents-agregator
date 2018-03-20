package ru.ezhov.template.core;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UUIDNameTest {
    @Test
    public void getOk() throws Exception {
        Name name = new UUIDName();
        String nameText = name.get();
        System.out.println(nameText);
        assertNotNull(name);
    }
}