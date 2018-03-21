package ru.ezhov.template.core.template;

import ru.ezhov.template.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class DbTemplateId implements TemplateId {

    private final Source<DataSource> source;

    public DbTemplateId(Source<DataSource> source) {
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
