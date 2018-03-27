package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Fields;

import java.util.Date;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Document {
    int id();

    String name();

    void name(String name);

    boolean active();

    void active(boolean active);

    String tableName();

    void tableName(String tableName);

    String username();

    void username(String username);

    Date addDt();

    void addDt(Date addDt);

    String description();

    void description(String description);

    Fields fields();
}
