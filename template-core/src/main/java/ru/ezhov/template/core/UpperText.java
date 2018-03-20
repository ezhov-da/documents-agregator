package ru.ezhov.template.core;

public class UpperText implements Text {

    private String text;

    public UpperText(String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return text.toUpperCase();
    }
}
