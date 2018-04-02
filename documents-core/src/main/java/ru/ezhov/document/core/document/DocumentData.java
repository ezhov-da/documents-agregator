package ru.ezhov.document.core.document;

import ru.ezhov.document.core.inputdoc.Row;

import java.util.Iterator;

public interface DocumentData {
    Iterator<Row> rows() throws Exception;
}
