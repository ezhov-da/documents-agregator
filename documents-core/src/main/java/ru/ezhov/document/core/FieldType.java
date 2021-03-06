package ru.ezhov.document.core;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public enum FieldType {
    STRING(1), INTEGER(2);

    private int idType;

    FieldType(int idType) {
        this.idType = idType;
    }

    public int id() {
        return idType;
    }
}
