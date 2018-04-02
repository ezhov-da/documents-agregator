package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.name.TableName;

import java.util.Date;
import java.util.List;

public class NewDocument implements Document {

    private DocumentId documentId;
    private String name;
    private String description;
    private boolean active;
    private TableName tableName;
    private String username;
    private Fields fields;


    public NewDocument(
            DocumentId documentId,
            String name,
            String description,
            TableName tableName,
            String username,
            Fields fields
    ) {
        this.documentId = documentId;
        this.name = name;
        this.description = description;
        this.tableName = tableName;
        this.username = username;
        this.fields = fields;
    }

    @Override
    public int id() {
        return documentId.get();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public String tableName() {
        return tableName.get();
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public Date addDt() {
        return new Date();
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Fields fields() {
        return fields;
    }

    @Override
    public void createFields(List<Field> fields) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Field editFields(List<Field> fields) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Field deleteFields(List<Field> fields) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentData documentData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(DocumentData documentData) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void edit(DocumentData documentData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(DocumentData documentData) {
        throw new UnsupportedOperationException();
    }
}
