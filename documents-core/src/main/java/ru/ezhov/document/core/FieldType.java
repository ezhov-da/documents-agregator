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

    static FieldType valueOf(int id) {
        for (FieldType fieldType : FieldType.values()) {
            if (fieldType.id() == id) {
                return fieldType;
            }
        }
        throw new IllegalArgumentException("Неподдерживаемая ID типа: " + id);
    }
}
