package ru.ezhov.document.core.util.text;

public class WithoutDash implements Text {

    private final Text text;

    public WithoutDash(Text text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return text.asString().replace("-", "");
    }
}
