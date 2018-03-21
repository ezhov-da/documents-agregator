package ru.ezhov.template.core.text;

import ru.ezhov.template.core.text.Text;

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
