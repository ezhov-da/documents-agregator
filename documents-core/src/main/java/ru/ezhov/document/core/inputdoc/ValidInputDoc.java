package ru.ezhov.document.core.inputdoc;

import ru.ezhov.document.core.inputdoc.Row;

import java.util.Iterator;

public interface ValidInputDoc<T> {
    Iterator<Row<T>> valid() throws Exception;
}
