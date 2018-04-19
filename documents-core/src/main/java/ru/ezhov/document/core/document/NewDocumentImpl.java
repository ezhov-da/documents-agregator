package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.NewFields;
import ru.ezhov.document.core.name.TableName;

import java.util.Date;

public class NewDocumentImpl implements NewDocument {

    private DocumentId documentId;
    private String name;
    private String description;
    private boolean active;
    private TableName tableName;
    private String username;
    private NewFields fields;


    public NewDocumentImpl(
            DocumentId documentId,
            String name,
            String description,
            TableName tableName,
            String username,
            NewFields fields
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
    public NewFields fields() {
        return fields;
    }
}
