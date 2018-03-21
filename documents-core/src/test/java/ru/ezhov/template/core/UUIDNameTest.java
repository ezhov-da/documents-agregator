package ru.ezhov.document.core;

import org.junit.Test;
import ru.ezhov.document.core.name.Name;
import ru.ezhov.document.core.name.UUIDName;

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