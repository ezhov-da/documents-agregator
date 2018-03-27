package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;

import java.util.Date;

public interface Fields {
    Field id();

    int idDocument();

    void idDocument(int idDocument);

    String name();

    void name(String name);

    String description();

    void description(String description);

    boolean active();

    void active(boolean active);

    String columnName();

    void columnName(String columnName);

    FieldType type();

    void type(FieldType type);

    int length();

    void length(int length);

    boolean empty();

    void empty(boolean empty);

    Order order();

    void order(Order order);

    String username();

    void username(String username);

    Date addDt();
}