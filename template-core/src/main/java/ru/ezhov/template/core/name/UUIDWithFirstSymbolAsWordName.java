package ru.ezhov.template.core.name;

public final class UUIDWithFirstSymbolAsWordName implements Name {
    private final Name name;

    public UUIDWithFirstSymbolAsWordName(Name name) {
        this.name = name;
    }

    @Override
    public String get() {
        String finalName = null;

        while (finalName == null) {
            String nameText = name.get();
            String textCheck = nameText.substring(0, 1).replaceFirst("\\w", "");

            if ("".equals(textCheck)) {
                finalName = nameText;
            }
        }

        return finalName;
    }
}
