package ru.ezhov.template.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
class Sheet {
    private static final Logger LOG = Logger.getLogger(Sheet.class.getName());

    private List<List<Cell>> cells;

    public Sheet() {
        cells = new ArrayList<>(100);
    }

    public long rowCount() {
        return cells.size();
    }

    public long columnCount(Row row) {
        return cells.get(row.number()).size();
    }

}
