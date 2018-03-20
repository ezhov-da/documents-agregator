package ru.ezhov.template.core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FirstSymbolAsWordNameTest {
    @Test
    public void getOk() throws Exception {
        Name name = new FirstSymbolAsWordName(() -> "fac5071d-e26d-41ba-aeb6-81ad9035382c");
        String firstWord = name.get().substring(0, 1);
        System.out.println(firstWord);

        assertTrue("".equals(firstWord.replaceFirst("\\w", "")));
    }

}