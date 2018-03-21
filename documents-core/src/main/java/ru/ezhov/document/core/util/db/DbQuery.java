package ru.ezhov.document.core.util.db;

import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.util.text.Text;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbQuery implements Query {
    private final Source<DataSource> source;
    private final Text query;

    public DbQuery(Text query, Source<DataSource> source) {
        this.source = source;
        this.query = query;
    }

    @Override
    public void executeStatement() {
        System.out.println(query.asString());

        try (Connection connection = source.get().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(query.asString());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить запрос", e);
        }
    }
}
