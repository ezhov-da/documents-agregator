package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Type;

public interface Cell {
    String name();

    String columnName();

    void name(String name);

    Type type();

    void type(Type type);

    int length();

    void length(int size);

    Order order();

    void order(Order order);
}
