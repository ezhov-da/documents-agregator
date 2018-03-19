package ru.ezhov.template.core;

import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
class Cell {
    private static final Logger LOG = Logger.getLogger(Cell.class.getName());

    private Type type;

    private Row row;
    private Column column;

    private Object data;

    public Cell(Type type, Column column, Row row, Object data) {
        this.type = type;
        this.column = column;
        this.row = row;
        this.data = data;
    }

}
