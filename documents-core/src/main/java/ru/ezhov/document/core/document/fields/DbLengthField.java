package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbLengthField implements Field {
    private int id;
    private Source<DataSource> source;

    public DbLengthField(int id, Source<DataSource> source) {
        this.id = id;
        this.source = source;
    }
    @Override
    public int id() {
        return id;
    }

    @Override
    public Integer data() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.LENGTH FROM T_DOCUMENT_FIELD t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    } else {
                        throw new RuntimeException("Не найдено поле");
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
