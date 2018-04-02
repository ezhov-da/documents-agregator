package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;

import java.util.Date;

public class DbField implements Field {

    @Override
    public int id() {
        return 0;
    }

    @Override
    public int idDocument() {
        return 0;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public boolean key() {
        return false;
    }

    @Override
    public String columnName() {
        return null;
    }

    @Override
    public FieldType type() {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public Order order() {
        return null;
    }

    @Override
    public String username() {
        return null;
    }

    @Override
    public Date addDt() {
        return null;
    }
}
