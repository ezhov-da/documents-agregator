package ru.ezhov.template.core.name;

import java.util.UUID;

public class UUIDName implements Name {
    @Override
    public String get() {
        return UUID.randomUUID().toString();
    }
}
