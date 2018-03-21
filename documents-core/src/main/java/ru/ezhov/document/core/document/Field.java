package ru.ezhov.document.core.document;

import ru.ezhov.document.core.FieldType;

public interface Field {
    String name();

    String columnName();

    void name(String name);

    FieldType type();

    void type(FieldType fieldType);

    int length();

    void length(int size);

    Order order();

    void order(Order order);

    boolean active();
}
