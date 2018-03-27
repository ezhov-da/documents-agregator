package ru.ezhov.document.core.document.prototype;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Order;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;

import java.util.Date;

public class DbFields implements Fields {
    private int idDocument;

    public DbFields(int idDocument) {
        this.idDocument = idDocument;
    }

    @Override
    public Field id() {
        return null;
    }

    @Override
    public int idDocument() {
        return 0;
    }

    @Override
    public void idDocument(int idDocument) {

    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void name(String name) {

    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public void description(String description) {

    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public void active(boolean active) {

    }

    @Override
    public String columnName() {
        return null;
    }

    @Override
    public void columnName(String columnName) {

    }

    @Override
    public FieldType type() {
        return null;
    }

    @Override
    public void type(FieldType type) {

    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public void length(int length) {

    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public void empty(boolean empty) {

    }

    @Override
    public Order order() {
        return null;
    }

    @Override
    public void order(Order order) {

    }

    @Override
    public String username() {
        return null;
    }

    @Override
    public void username(String username) {

    }

    @Override
    public Date addDt() {
        return null;
    }
}
