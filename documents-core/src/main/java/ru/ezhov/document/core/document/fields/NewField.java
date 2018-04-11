package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;

import java.util.Date;

/**
 * Created by ezhov_da on 11.04.2018.
 */
public interface NewField {
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
