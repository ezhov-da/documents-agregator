package ru.ezhov.document.core.document;

import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class DbDocumentId implements DocumentId {

    private final Source<DataSource> source;

    public DbDocumentId(Source<DataSource> source) {
        this.source = source;
    }

    @Override
    public int get() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT (SELECT NEXT VALUE FOR SEQ_TEMPLATE) AS ID;")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
