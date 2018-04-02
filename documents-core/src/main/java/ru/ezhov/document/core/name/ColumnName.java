package ru.ezhov.document.core.name;

import ru.ezhov.document.core.util.text.TextOf;
import ru.ezhov.document.core.util.text.WithoutDash;

public class ColumnName implements Name {
    @Override
    public String get() {
        return "C" + new WithoutDash(new TextOf(new UuidName().get())).asString();
    }
}
