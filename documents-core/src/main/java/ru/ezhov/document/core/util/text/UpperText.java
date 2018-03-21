package ru.ezhov.document.core.util.text;

public class UpperText implements Text {

    private Text text;

    public UpperText(Text text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return text.asString().toUpperCase();
    }
}
