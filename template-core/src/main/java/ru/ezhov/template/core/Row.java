package ru.ezhov.template.core;

import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
class Row {
    private static final Logger LOG = Logger.getLogger(Row.class.getName());

    private int number;

    public Row(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }

}
