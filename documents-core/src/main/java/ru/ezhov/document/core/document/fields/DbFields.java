package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbFields implements Fields {

    private int idDocument;
    private Source<DataSource> source;

    public DbFields(int idDocument, Source<DataSource> source) {
        this.idDocument = idDocument;
        this.source = source;
    }

    public Iterator<Field> all() {
        List<Field> fields = new ArrayList<>();
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "SELECT t0.ID FROM T_DOCUMENT_FIELD t0 WHERE t0.ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, idDocument);
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        while (resultSet.next()) {
                            int idField = resultSet.getInt(1);
                            fields.add(new DbField(idField, idDocument, source));
                        }
                        return fields.iterator();
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

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
