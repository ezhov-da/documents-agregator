package ru.ezhov.document.core.document;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.name.Name;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbDocument implements Document {

    private int id;
    private Source<DataSource> source;

    public DbDocument(int id, Source<DataSource> source) {
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
                             connection.prepareStatement("SELECT NAME FROM T_DOCUMENT WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
    public boolean active() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ACTIVE FROM T_DOCUMENT WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getBoolean(1);
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
    public String tableName() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT TABLE_NAME FROM T_DOCUMENT WHERE ID = ?")) {
                    preparedStatement.setInt(1, id);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
    public String username() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT USERNAME FROM T_DOCUMENT WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
    public Date addDt() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ADD_DT FROM T_DOCUMENT WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getTimestamp(1);
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
    public String description() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT DESCRIPTION FROM T_DOCUMENT WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
    public List<Field> fields() {
        List<Field> fields = new ArrayList<>();

        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "SELECT t0.ID FROM T_DOCUMENT_FIELD t0 WHERE t0.ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt(1);
                            fields.add(new DbField(id, this, source));
                        }

                        return fields;
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Field addField(String name, Name columnName, String description, FieldType fieldType, int length, Order order, String username) {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "INSERT INTO T_DOCUMENT_FIELD (\n" +
                                             "  ID_DOCUMENT,\n" +
                                             "  NAME,\n" +
                                             "  DESCRIPTION,\n" +
                                             "  COLUMN_NAME,\n" +
                                             "  ID_FIELD_TYPE,\n" +
                                             "  LENGTH,\n" +
                                             "  ORDERR,\n" +
                                             "  USERNAME\n" +
                                             ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, description);
                    preparedStatement.setString(4, columnName.get());
                    preparedStatement.setInt(5, fieldType.id());
                    preparedStatement.setInt(6, length);
                    preparedStatement.setString(7, order.value());
                    preparedStatement.setString(8, username);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new RuntimeException(new SQLException("Ячейка не добавлена в шаблон"));
                    }

                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            return field(generatedKeys.getInt(1));
                        } else {
                            throw new RuntimeException(new SQLException("Ячейка не добавлена в шаблон"));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Field field(int id) {
        return new DbField(id, this, source);
    }

    @Override
    public String toString() {
        return "DbDocument{" +
                "id=" + id +
                '}';
    }
}
