package ru.ezhov.template.core;

public final class FirstSymbolAsWordName implements Name {
    private final Name name;

    public FirstSymbolAsWordName(Name name) {
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
