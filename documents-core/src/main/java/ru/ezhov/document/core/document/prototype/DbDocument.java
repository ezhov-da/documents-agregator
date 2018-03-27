package ru.ezhov.document.core.document.prototype;

import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Fields;

import java.util.Date;

public class DbDocument implements Document {

    private int id;
    private Fields fields;

    public DbDocument(int id) {
        this.id = id;
        this.fields = new DbFields(this.id);
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void name(String name) {

    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public void active(boolean active) {

    }

    @Override
    public String tableName() {
        return null;
    }

    @Override
    public void tableName(String tableName) {

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

    @Override
    public void addDt(Date addDt) {

    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public void description(String description) {

    }

    @Override
    public Fields fields() {
        return fields;
    }
}
