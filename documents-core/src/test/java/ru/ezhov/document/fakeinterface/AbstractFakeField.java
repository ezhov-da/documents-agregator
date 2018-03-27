package ru.ezhov.document.fakeinterface;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.Order;

public abstract class AbstractFakeField implements Field {
    @Override
    public String name() {
        return null;
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
    public Order order() {
        return null;
    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public String description() {
        return null;
    }
}
