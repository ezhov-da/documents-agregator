package ru.ezhov.document.core.inputdoc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringRow implements Row<String> {

    private Iterator<String> iterator;

    public StringRow(Iterator<String> iterator) {
        this.iterator = iterator;
    }

    public StringRow(List<String> strings) {
        this(strings.iterator());
    }

    public StringRow(String... strings) {
        this(Arrays.asList(strings).iterator());
    }

    @Override
    public Iterator<String> values() {
        return iterator;
    }
}
