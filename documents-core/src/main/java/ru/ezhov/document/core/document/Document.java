package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.document.fields.NewField;

import java.util.Date;
import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Document {
    int id() throws Exception;

    String name() throws Exception;

    boolean active() throws Exception;

    String tableName() throws Exception;

    String username() throws Exception;

    Date addDt() throws Exception;

    String description() throws Exception;

    Fields fields() throws Exception;

    void createFields(List<NewField> fields) throws Exception;

    Field editFields(List<Field> fields) throws Exception;

    Field deleteFields(List<Field> fields) throws Exception;

    DocumentData documentData() throws Exception;

    void addData(DocumentData documentData) throws Exception;

    void editData(DocumentData documentData) throws Exception;

    void deleteData(DocumentData documentData) throws Exception;
}
