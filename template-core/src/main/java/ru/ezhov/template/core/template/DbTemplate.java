package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbTemplate implements Template {

    private int id;
    private Source<DataSource> source;

    public DbTemplate(int id, Source<DataSource> source) {
        this.id = id;
        this.source = source;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareCall("SELECT NAME FROM T_TEMPLATE WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        if (resultSet.next()) {
                            return resultSet.getString(1);
                        } else {
                            throw new RuntimeException("Нет такой записи");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return "DbTemplate{" +
                "id=" + id +
                '}';
    }
}
