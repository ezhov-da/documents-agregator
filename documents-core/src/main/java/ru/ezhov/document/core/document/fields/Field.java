package ru.ezhov.document.core.document.fields;

public interface Field {

    int id();

    Object data();

    void save();
}
