package ru.ezhov.template.core.text;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public final class TextOfInputStream implements Text {
    private static final Logger LOG = Logger.getLogger(TextOfInputStream.class.getName());

    private final InputStream src;

    public TextOfInputStream(InputStream src) {
        this.src = src;
    }

    @Override
    public String asString() {
        StringBuilder stringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(src);
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        return stringBuilder.toString();
    }
}
