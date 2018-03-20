package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Source;
import ru.ezhov.template.core.Type;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class DbCell implements Cell {

    private Template template;
    private final int id;
    private final Source<DataSource> source;

    public DbCell(final int id, final Template template, final Source<DataSource> source) {
        this.template = template;
        this.id = id;
        this.source = source;
    }

    public String name() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT NAME FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, template.id());

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
                    preparedStatement.setInt(2, template.id());

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

    public Type type() {
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
                    preparedStatement.setInt(2, template.id());

                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        resultSet.next();
                        return Type.valueOf(resultSet.getString(1));
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void type(Type type) {
        throw new UnsupportedOperationException("OOPS");
    }

    public int length() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT LENGTH FROM T_TEMPLATE_META_INFO WHERE ID = ? AND ID_TEMPLATE = ?;")) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, template.id());

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
                    preparedStatement.setInt(2, template.id());

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
}
