package ru.ezhov.template.core;

public class StandartName implements Name {
    @Override
    public String get() {
        return new UpperText(
                new FirstSymbolAsWordName(
                        new WithoutDashName(
                                new UUIDName()
                        )
                ).get()
        ).asString();
    }
}
