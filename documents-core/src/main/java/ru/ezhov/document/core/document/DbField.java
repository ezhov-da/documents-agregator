package ru.ezhov.document.core.document;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class DbField implements Field {

    private final int id;
    private final Source<DataSource> source;
    private Document document;

    public DbField(final int id, final Document document, final Source<DataSource> source) {
        this.document = document;
        this.id = id;
        this.source = source;
    }

    public String name() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT NAME FROM T_DOCUMENT_FIELD WHERE ID = ? AND ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return resultSet.getString(1);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String columnName() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT COLUMN_NAME FROM T_DOCUMENT_FIELD WHERE ID = ? AND ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return resultSet.getString(1);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void name(String name) {
        throw new UnsupportedOperationException("OOPS");
    }

    public FieldType type() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT \n" +
                                     "    t1.NAME\n" +
                                     "FROM T_DOCUMENT_FIELD t0\n" +
                                     "  INNER JOIN  T_DOCUMENT_FIELD_TYPE t1 ON t0.ID_FIELD_TYPE = t1.ID \n" +
                                     "WHERE \n" +
                                     "  t0.ID = ? \n" +
                                     "  AND \n" +
                                     "  t0.ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return FieldType.valueOf(resultSet.getString(1));
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void type(FieldType fieldType) {
        throw new UnsupportedOperationException("OOPS");
    }

    public int length() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT LENGTH FROM T_DOCUMENT_FIELD WHERE ID = ? AND ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

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

    public void length(int size) {
        throw new UnsupportedOperationException("OOPS");
    }

    public Order order() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ORDERR FROM T_DOCUMENT_FIELD WHERE ID = ? AND ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return Order.getOrder(resultSet.getString(1));
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void order(Order order) {
        throw new UnsupportedOperationException("OOPS");
    }

    @Override
    public boolean active() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ACTIVE FROM T_DOCUMENT_FIELD WHERE ID = ? AND ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return resultSet.getBoolean(1);
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
                             connection.prepareStatement("SELECT DESCRIPTION FROM T_DOCUMENT_FIELD WHERE ID = ? AND ID_DOCUMENT = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, document.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return resultSet.getString(1);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
