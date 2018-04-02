package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;

import java.util.Date;
import java.util.List;

public class EditDocument implements Document {
    private int idDocument;
    private String name;
    private String description;
    private boolean active;
    private String username;

    public EditDocument(int idDocument, String name, String description, boolean active, String username) {
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
        return null;
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
        return null;
    }

    @Override
    public void createFields(List<Field> fields) {

    }

    @Override
    public Field editFields(List<Field> fields) {
        return null;
    }

    @Override
    public Field deleteFields(List<Field> fields) {
        return null;
    }

    @Override
    public DocumentData documentData() {
        return null;
    }

    @Override
    public void add(DocumentData documentData) {

    }

    @Override
    public void edit(DocumentData documentData) {

    }

    @Override
    public void delete(DocumentData documentData) {

    }
}
