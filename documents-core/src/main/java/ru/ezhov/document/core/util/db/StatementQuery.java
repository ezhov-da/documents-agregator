package ru.ezhov.document.core.util.db;

import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.QueryText;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class StatementQuery implements Query {
    private final Source<DataSource> source;
    private final QueryText query;

    public StatementQuery(QueryText query, Source<DataSource> source) {
        this.source = source;
        this.query = query;
    }

    @Override
    public void execute() {
        System.out.println(query.text().asString());

        try (Connection connection = source.get().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(query.text().asString());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить запрос", e);
        }
    }
}
