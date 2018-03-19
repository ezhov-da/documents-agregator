package ru.ezhov.template.core;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public class InputStreamToText implements Transform<String> {
    private static final Logger LOG = Logger.getLogger(InputStreamToText.class.getName());

    private final InputStream src;

    public InputStreamToText(InputStream src) {
        this.src = src;
    }

    @Override
    public String value() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(src);
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        return stringBuilder.toString();
    }
}
