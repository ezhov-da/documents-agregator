package ru.ezhov.document.core;

import org.junit.Test;
import ru.ezhov.document.core.name.Name;
import ru.ezhov.document.core.name.UuidName;
import ru.ezhov.document.core.name.UuidWithFirstSymbolAsWordName;

import static org.junit.Assert.assertTrue;

public class UuidWithFirstSymbolAsWordNameTest {
    @Test
    public void getOk() throws Exception {
        Name name = new UuidWithFirstSymbolAsWordName(new UuidName());
        String firstWord = name.get().substring(0, 1);
        System.out.println(firstWord);

        assertTrue("".equals(firstWord.replaceFirst("\\w", "")));
    }

}