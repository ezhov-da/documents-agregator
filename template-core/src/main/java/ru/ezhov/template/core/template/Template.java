package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Name;
import ru.ezhov.template.core.Type;

import java.util.Date;
import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Template {
    int id();

    String name();

    boolean active();

    String tableName();

    String username();

    Date addDt();

    List<Cell> allCells();

    Cell addCell(String name, Name columnName, Type type, int length, Order order, String username);

    Cell cell(int id);

    //TODO: обновление ячейки
    //TODO: удаление ячейки
}
