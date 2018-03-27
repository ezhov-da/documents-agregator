package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbActiveField implements ActiveField {

    private int id;
    private Source<DataSource> source;
    private boolean active;

    public DbActiveField(int id, Source<DataSource> source, boolean active) {
        this.id = id;
        this.source = source;
        this.active = active;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public Boolean data() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.ACTIVE FROM T_DOCUMENT_FIELD t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getBoolean(1);
                    } else {
                        throw new RuntimeException("Не найдено поле");
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void save() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE T_DOCUMENT_FIELD SET t0.ACTIVE = ? WHERE t0.ID = ?;")) {
                preparedStatement.setBoolean(1, active);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
