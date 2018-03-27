package ru.ezhov.document.fakeinterface;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.Order;
import ru.ezhov.document.core.name.Name;

import java.util.Date;
import java.util.List;

public abstract class AbstractFakeDocument implements Document {
    @Override
    public int id() {
        return 0;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public String tableName() {
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

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<Field> fields() {
        return null;
    }

    @Override
    public Field addField(String name, Name columnName, String description, FieldType fieldType, int length, Order order, String username) {
        return null;
    }

    @Override
    public Field field(int id) {
        return null;
    }
}
