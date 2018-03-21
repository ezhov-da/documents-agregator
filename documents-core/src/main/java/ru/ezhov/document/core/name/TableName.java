package ru.ezhov.document.core.name;

public class TableName implements Name {
    @Override
    public String get() {
        return "T" + System.currentTimeMillis();
    }
}
