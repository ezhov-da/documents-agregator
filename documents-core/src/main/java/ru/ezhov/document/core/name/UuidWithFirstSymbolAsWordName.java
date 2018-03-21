package ru.ezhov.document.core.name;

public final class UuidWithFirstSymbolAsWordName implements Name {
    private final Name name;

    public UuidWithFirstSymbolAsWordName(Name name) {
        this.name = name;
    }

    @Override
    public String get() {
        String finalName = null;

        while (finalName == null) {
            String nameText = name.get();
            String textCheck = nameText.substring(0, 1).replaceFirst("^[a-z]", "");

            if ("".equals(textCheck)) {
                finalName = nameText;
            }
        }

        return finalName;
    }
}
