package ru.ezhov.document.core.document.fields;

import java.util.List;

public class NewFieldsImpl implements NewFields {

    private List<NewField> fields;

    public NewFieldsImpl(List<NewField> fields) {
        this.fields = fields;
    }

    @Override
    public List<NewField> all() {
        return fields;
    }
}
