package ru.ezhov.template.core.name;

import ru.ezhov.template.core.text.TextOf;
import ru.ezhov.template.core.text.UpperText;
import ru.ezhov.template.core.text.WithoutDash;

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
