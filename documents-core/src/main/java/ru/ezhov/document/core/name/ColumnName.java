package ru.ezhov.document.core.name;

public class ColumnName implements Name {
    @Override
    public String get() {
        return "C" + System.currentTimeMillis();
    }
}
