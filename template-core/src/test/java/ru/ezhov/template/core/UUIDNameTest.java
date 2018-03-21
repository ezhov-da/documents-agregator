package ru.ezhov.template.core;

import org.junit.Test;
import ru.ezhov.template.core.name.Name;
import ru.ezhov.template.core.name.UUIDName;

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