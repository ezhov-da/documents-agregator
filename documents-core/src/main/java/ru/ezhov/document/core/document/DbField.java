package ru.ezhov.document.core.document;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class DbField implements Field {

    private Document document;
    private final int id;
    private final Source<DataSource> source;

    public DbField(final int id, final Document document, final Source<DataSource> source) {
        this.document = document;
        this.id = id;
        this.source = source;
    }

    public String name() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT NAME FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

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
                             connection.prepareStatement("SELECT COLUMN_NAME FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

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
                                     "FROM T_TEMPLATE_META_INFO t0\n" +
                                     "  INNER JOIN  T_TYPE t1 ON t0.ID_TYPE = t1.ID \n" +
                                     "WHERE \n" +
                                     "  t0.ID = ? \n" +
                                     "  AND \n" +
                                     "  t0.ID_TEMPLATE = ?;")) {

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
                             connection.prepareStatement("SELECT LENGTH FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

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
                             connection.prepareStatement("SELECT ORDERR FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

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
                             connection.prepareStatement("SELECT ACTIVE FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

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
}
