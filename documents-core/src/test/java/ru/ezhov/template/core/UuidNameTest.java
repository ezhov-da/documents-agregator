package ru.ezhov.document.core;

import org.junit.Test;
import ru.ezhov.document.core.name.Name;
import ru.ezhov.document.core.name.UuidName;

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