package ru.ezhov.document.core.name;

import ru.ezhov.document.core.util.text.TextOf;
import ru.ezhov.document.core.util.text.UpperText;
import ru.ezhov.document.core.util.text.WithoutDash;

public class DefaultUuidName implements Name {
    @Override
    public String get() {
        return new UpperText(
                new WithoutDash(
                        new TextOf(
                                new UuidWithFirstSymbolAsWordName(new UuidName()).get()
                        )
                )
        ).asString();
    }
}
