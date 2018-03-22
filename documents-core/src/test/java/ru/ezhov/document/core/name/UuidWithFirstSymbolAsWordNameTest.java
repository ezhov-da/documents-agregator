package ru.ezhov.document.core.name;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UuidWithFirstSymbolAsWordNameTest {
    @Test
    public void getOk() throws Exception {
        Name name = new UuidWithFirstSymbolAsWordName(new UuidName());
        String firstWord = name.get().substring(0, 1);
        System.out.println(firstWord);

        assertTrue("".equals(firstWord.replaceFirst("^[a-z]", "")));
    }

}