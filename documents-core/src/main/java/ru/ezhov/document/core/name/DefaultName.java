package ru.ezhov.document.core.name;

import ru.ezhov.document.core.util.text.TextOf;
import ru.ezhov.document.core.util.text.UpperText;
import ru.ezhov.document.core.util.text.WithoutDash;

public class DefaultName implements Name {
    @Override
    public String get() {
        return new UpperText(
                new WithoutDash(
                        new TextOf(
                                new UUIDWithFirstSymbolAsWordName(new UUIDName()).get()
                        )
                )
        ).asString();
    }
}
