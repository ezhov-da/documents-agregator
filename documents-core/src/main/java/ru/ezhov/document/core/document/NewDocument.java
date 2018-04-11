package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.NewFields;

import java.util.Date;

/**
 * Created by ezhov_da on 11.04.2018.
 */
public interface NewDocument {
    int id();

    String name();

    boolean active();

    String tableName();

    String username();

    Date addDt();

    String description();

    NewFields fields();
}
