package ru.ezhov.document.core.document;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.name.Name;

import java.util.Date;
import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Document {
    int id();

    String name();

    boolean active();

    String tableName();

    String username();

    Date addDt();

    List<Field> allCells();

    Field addCell(String name, Name columnName, FieldType fieldType, int length, Order order, String username);

    Field cell(int id);

    //TODO: обновление ячейки
    //TODO: удаление ячейки
}
