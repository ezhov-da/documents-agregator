package ru.ezhov.document.core.name;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UuidNameTest {
    @Test
    public void getOk() throws Exception {
        Name name = new UuidName();
        String nameText = name.get();
        System.out.println(nameText);

        assertNotNull(name);
    }
}