package ru.ezhov.template.core;

public class WithoutDashName implements Name {

    private final Name name;

    public WithoutDashName(Name name) {
        this.name = name;
    }

    @Override
    public String get() {
        return name.get().replace("-", "");
    }
}
