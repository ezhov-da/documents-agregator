package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;

import java.util.Date;

public interface Field {

    int id();

    int idDocument();

    String name();

    String description();

    boolean active();

    boolean key();

    String columnName();

    FieldType type();

    int length();

    boolean empty();

    Order order();

    String username();

    Date addDt();
}
