package ru.ezhov.document.core.name;

import java.util.UUID;

public class UuidName implements Name {
    @Override
    public String get() {
        return UUID.randomUUID().toString();
    }
}
