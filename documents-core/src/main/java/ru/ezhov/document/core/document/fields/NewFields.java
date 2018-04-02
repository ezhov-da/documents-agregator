package ru.ezhov.document.core.document.fields;

import java.util.List;

public class NewFields implements Fields {

    private List<Field> fields;

    public NewFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public List<Field> all() {
        return fields;
    }

    @Override
    public Field field(int id) {
        throw new UnsupportedOperationException();
    }
}
