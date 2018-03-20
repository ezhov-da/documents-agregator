package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Name;
import ru.ezhov.template.core.Source;
import ru.ezhov.template.core.Type;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                             connection.prepareStatement("SELECT NAME FROM T_TEMPLATE WHERE ID = ?")) {
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
    public boolean isActive() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ACTIVE FROM T_TEMPLATE WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
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
                             connection.prepareStatement("SELECT TABLE_NAME FROM T_TEMPLATE WHERE ID = ?")) {
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
    public String username() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT USERNAME FROM T_TEMPLATE WHERE ID = ?")) {
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
    public Date addDt() {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ADD_DT FROM T_TEMPLATE WHERE ID = ?")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
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
    public List<Cell> allCells() {
        List<Cell> cells = new ArrayList<>();

        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "SELECT t0.ID FROM T_TEMPLATE_META_INFO t0 WHERE t0.ID_TEMPLATE = ?;")) {

                    preparedStatement.setInt(1, id);
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt(1);
                            cells.add(new DbCell(id, this, source));
                        }

                        return cells;
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Cell addCell(String name, Name columnName, Type type, int length, Order order, String username) {
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "INSERT INTO T_TEMPLATE_META_INFO (\n" +
                                             "  ID_TEMPLATE,\n" +
                                             "  NAME,\n" +
                                             "  COLUMN_NAME,\n" +
                                             "  ID_TYPE,\n" +
                                             "  LENGTH,\n" +
                                             "  ORDERR,\n" +
                                             "  USERNAME\n" +
                                             ") VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, columnName.get());
                    preparedStatement.setInt(4, type.id());
                    preparedStatement.setInt(5, length);
                    preparedStatement.setString(6, order.value());
                    preparedStatement.setString(7, username);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new RuntimeException(new SQLException("Ячейка не добавлена в шаблон"));
                    }

                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            return cell(generatedKeys.getInt(1));
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
    public Cell cell(int id) {
        return new DbCell(id, this, source);
    }

    @Override
    public String toString() {
        return "DbTemplate{" +
                "id=" + id +
                '}';
    }
}
