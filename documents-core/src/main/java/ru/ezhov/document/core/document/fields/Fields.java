package ru.ezhov.document.core.document.fields;

import java.util.List;

public interface Fields {
    List<Field> all();

    Field field(int id);
}
