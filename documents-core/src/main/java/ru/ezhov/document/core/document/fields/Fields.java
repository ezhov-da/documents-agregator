package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;

import java.util.Date;

public interface Fields {
    List<Field> all();
    Field create();
    Field edit();
    Field delete(int idField);
}
