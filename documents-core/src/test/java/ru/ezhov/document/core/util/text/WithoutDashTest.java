package ru.ezhov.document.core.util.text;

import org.junit.Test;
import ru.ezhov.document.core.util.text.Text;
import ru.ezhov.document.core.util.text.WithoutDash;

import static org.junit.Assert.assertTrue;

public class WithoutDashTest {
    @Test
    public void getOk() throws Exception {
        Text text = new WithoutDash(() -> "fac5071d-e26d-41ba-aeb6-81ad9035382c");
        String n = text.asString();

        assertTrue(n.length() == 32);
    }

}