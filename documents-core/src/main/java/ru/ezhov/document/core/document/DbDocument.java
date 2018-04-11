package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.DbFields;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.document.fields.NewField;
import ru.ezhov.document.core.inputdoc.Row;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.QueryText;
import ru.ezhov.document.core.table.h2.H2InsertTableQueryText;
import ru.ezhov.document.core.table.h2.H2SelectAllTableQueryText;

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
                                 "SELECT t0.NAME FROM T_DOCUMENT t0 WHERE t0.ID = ?;")) {
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
                                 "SELECT t0.ACTIVE FROM T_DOCUMENT t0 WHERE t0.ID = ?;")) {
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
                                 "SELECT t0.USERNAME FROM T_DOCUMENT t0 WHERE t0.ID = ?;")) {
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
                                 "SELECT t0.ADD_DT FROM T_DOCUMENT t0 WHERE t0.ID = ?;")) {
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
                                 "SELECT t0.DESCRIPTION FROM T_DOCUMENT t0 WHERE t0.ID = ?;")) {
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
        return new DbFields(id, source);
    }

    @Override
    public void createFields(List<NewField> fields) {
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

                for (NewField field : fields) {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Field deleteFields(List<Field> fields) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentData documentData() {
        return new DbDocumentData(source, new H2SelectAllTableQueryText(this));
    }

    @Override
    public void addData(DocumentData documentData) throws Exception {
        QueryText queryText = new H2InsertTableQueryText(this);
        Connection connection = null;
        try {
            connection = source.get().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(queryText.text().asString())) {
                Iterator<Row> rows = documentData.rows();
                while (rows.hasNext()) {
                    Row row = rows.next();
                    Iterator iterator = row.values();
                    int counter = 1;
                    while (iterator.hasNext()) {
                        preparedStatement.setObject(counter, iterator.next());
                        counter++;
                    }

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void editData(DocumentData documentData) {

    }

    @Override
    public void deleteData(DocumentData documentData) {

    }
}
