package ru.ezhov.template.core;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
class Workbook {
    private static final Logger LOG = Logger.getLogger(Workbook.class.getName());

    private String name;

    private List<Sheet> sheets;

    public Workbook(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public Workbook(Sheet... sheets) {
        this(Arrays.asList(sheets));
    }
}
