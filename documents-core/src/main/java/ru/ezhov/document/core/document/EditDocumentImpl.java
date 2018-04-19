package ru.ezhov.document.core.document;

import java.util.Date;

public class EditDocumentImpl implements EditDocument {
    private int idDocument;
    private String name;
    private String description;
    private boolean active;
    private String username;

    public EditDocumentImpl(int idDocument, String name, String description, boolean active, String username) {
        this.idDocument = idDocument;
        this.name = name;
        this.description = description;
        this.active = active;
        this.username = username;
    }

    @Override
    public int id() {
        return idDocument;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean active() {
        return active;
    }

    @Override
    public String tableName() {
        throw new UnsupportedOperationException();
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
}
