package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;
import ru.ezhov.document.core.name.ColumnName;

import java.util.Date;

public class NewFieldImpl implements NewField {

    private String name;
    private String description;
    private boolean active;
    private boolean key;
    private ColumnName columnName;
    private FieldType type;
    private int length;
    private boolean empty;
    private Order order;
    private String username;

    public NewFieldImpl(
            String name,
            String description,
            boolean active,
            boolean key,
            ColumnName columnName,
            FieldType type,
            int length,
            boolean empty,
            Order order,
            String username
    ) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.key = key;
        this.columnName = columnName;
        this.type = type;
        this.length = length;
        this.empty = empty;
        this.order = order;
        this.username = username;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public boolean active() {
        return active;
    }

    @Override
    public boolean key() {
        return key;
    }

    @Override
    public String columnName() {
        return columnName.get();
    }

    @Override
    public FieldType type() {
        return type;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean empty() {
        return empty;
    }

    @Override
    public Order order() {
        return order;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public Date addDt() {
        return new Date();
    }
}
