package ru.ezhov.document.core.document;

import ru.ezhov.document.core.FieldType;

public interface Field {
    String name();

    String columnName();

    FieldType type();

    int length();

    Order order();

    boolean active();

    String description();
}
