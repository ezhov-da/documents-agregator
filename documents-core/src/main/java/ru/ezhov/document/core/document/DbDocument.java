package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.DbFields;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

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
    public Fields fields() {
        return new DbFields(id, source);
    }

    @Override
    public String toString() {
        return "DbDocument{" +
                "id=" + id +
                '}';
    }
}
