package ru.ezhov.document.core.inputdoc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ObjectRow implements Row<Object> {

    private Iterator<Object> iterator;

    public ObjectRow(Iterator<Object> iterator) {
        this.iterator = iterator;
    }

    public ObjectRow(List<Object> objects) {
        this(objects.iterator());
    }

    public ObjectRow(Object... objects) {
        this(Arrays.asList(objects).iterator());
    }

    @Override
    public Iterator<Object> values() {
        return iterator;
    }
}
