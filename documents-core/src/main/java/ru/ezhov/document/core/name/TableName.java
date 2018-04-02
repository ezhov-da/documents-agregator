package ru.ezhov.document.core.name;

import ru.ezhov.document.core.util.text.TextOf;
import ru.ezhov.document.core.util.text.WithoutDash;

public class TableName implements Name {
    @Override
    public String get() {
        return "T_" + new WithoutDash(new TextOf(new UuidName().get())).asString();
    }
}
