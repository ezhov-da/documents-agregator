package ru.ezhov.template.core;

import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
class Column {
    private static final Logger LOG = Logger.getLogger(Column.class.getName());

    private int number;

    public Column(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }

}
