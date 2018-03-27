package ru.ezhov.document.core.document.fields;

import org.junit.Test;
import ru.ezhov.document.core.source.H2Source;

public class DbActiveFieldTest {
    @Test
    public void id() throws Exception {
        new DbActiveField(3, new H2Source(), false).save();
    }

    @Test
    public void data() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}