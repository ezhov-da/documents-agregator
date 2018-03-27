package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;

import java.util.Date;

public class DocumentTest implements Document {
    private Fields fields = new DbField();

    @Override
    public int id() {
        return 5000;
    }

    @Override
    public String name() {
        return "test";
    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public String tableName() {
        return "test";
    }

    @Override
    public String username() {
        return "test";
    }

    @Override
    public Date addDt() {
        return new Date();
    }

    @Override
    public String description() {
        return "test";
    }

    @Override
    public Fields fields() {
        return fields;
    }

    private class DbField implements Fields {

        @Override
        public Field id() {
            return null;
        }

        @Override
        public Field idDocument() {
            return null;
        }

        @Override
        public Field name() {
            return null;
        }

        @Override
        public Field description() {
            return null;
        }

        @Override
        public Field active() {
            return null;
        }

        @Override
        public Field columnName() {
            return null;
        }

        @Override
        public Field type() {
            return null;
        }

        @Override
        public Field length() {
            return null;
        }

        @Override
        public Field empty() {
            return null;
        }

        @Override
        public Field order() {
            return null;
        }

        @Override
        public Field username() {
            return null;
        }

        @Override
        public Field addDt() {
            return null;
        }
    }
}
