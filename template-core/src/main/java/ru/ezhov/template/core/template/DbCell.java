package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Type;

public class DbCell implements Cell {

    private Template template;
    private final int id;

    public DbCell(Template template, int id) {
        this.template = template;
        this.id = id;
    }

    public String name() {
        return null;
    }

    public void name(String name) {

    }

    public Type type() {
        return null;
    }

    public void type(Type type) {

    }

    public int length() {
        return 0;
    }

    public void length(int size) {

    }

    public Order order() {
        return null;
    }

    public void order(Order order) {

    }
}
