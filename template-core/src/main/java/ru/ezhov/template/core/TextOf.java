package ru.ezhov.template.core;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public final class TextOf implements Text {
    private static final Logger LOG = Logger.getLogger(TextOf.class.getName());

    private final InputStream src;

    public TextOf(InputStream src) {
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
