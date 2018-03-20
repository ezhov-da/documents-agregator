package ru.ezhov.template.core;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public enum Type {
    STRING(1), INTEGER(2);

    private int idType;

    Type(int idType) {
        this.idType = idType;
    }

    public int id() {
        return idType;
    }
}
