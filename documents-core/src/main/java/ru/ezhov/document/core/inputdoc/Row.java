package ru.ezhov.document.core.inputdoc;

import java.util.Iterator;

public interface Row<T> {
    Iterator<T> values();
}
