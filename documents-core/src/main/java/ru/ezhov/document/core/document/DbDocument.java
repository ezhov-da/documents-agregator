package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.inputdoc.Row;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DbDocument implements Document {

    private Source<DataSource> source;
    private int id;

    public DbDocument(Source<DataSource> source, int id) {
        this.id = id;
        this.source = source;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.NAME FROM T_DOCUMENT_FIELD t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
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
    public boolean active() {
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
    public String tableName() throws Exception {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.TABLE_NAME FROM T_DOCUMENT t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
                    } else {
                        throw new RuntimeException("Не найдено поле");
                    }
                }
            }
        }
    }

    @Override
    public String username() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.USERNAME FROM T_DOCUMENT_FIELD t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
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
    public Date addDt() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.ADD_DT FROM T_DOCUMENT_FIELD t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getDate(1);
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
    public String description() {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.DESCRIPTION FROM T_DOCUMENT_FIELD t0 WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
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
    public Fields fields() {
        return null;
    }

    @Override
    public void createFields(List<Field> fields) {
        Connection connection = null;
        try {
            connection = source.get().getConnection();
            connection.setAutoCommit(false);


            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO T_DOCUMENT_FIELD(" +
                            "ID_DOCUMENT, " +
                            "NAME," +
                            "DESCRIPTION," +
                            "ACTIVE," +
                            "KEY," +
                            "COLUMN_NAME," +
                            "ID_FIELD_TYPE," +
                            "LENGTH," +
                            "EMPTY," +
                            "ORDERR," +
                            "USERNAME," +
                            "ADD_DT ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "


            )) {

                for (Field field : fields) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, field.name());
                    preparedStatement.setString(3, field.description());
                    preparedStatement.setBoolean(4, field.active());
                    preparedStatement.setBoolean(5, field.key());
                    preparedStatement.setString(6, field.columnName());
                    preparedStatement.setInt(7, field.type().id());
                    preparedStatement.setInt(8, field.length());
                    preparedStatement.setBoolean(9, field.empty());
                    preparedStatement.setString(10, field.order().value());
                    preparedStatement.setString(11, field.username());
                    preparedStatement.setTimestamp(12, new Timestamp(field.addDt().getTime()));

                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
            }

            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Field editFields(List<Field> fields) {
        return null;
    }

    @Override
    public Field deleteFields(List<Field> fields) {
        return null;
    }

    @Override
    public DocumentData documentData() {
        return null;
    }

    @Override
    public void add(DocumentData documentData) {
        try {
            Iterator<Row> rows = documentData.rows();
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator iterator = row.values();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void edit(DocumentData documentData) {

    }

    @Override
    public void delete(DocumentData documentData) {

    }
}
