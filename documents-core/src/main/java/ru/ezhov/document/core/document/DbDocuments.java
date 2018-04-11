package ru.ezhov.document.core.document;

import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.h2.H2CreateTableQueryText;
import ru.ezhov.document.core.util.db.StatementQuery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DbDocuments implements Documents {
    private Source<DataSource> source;

    public DbDocuments(Source<DataSource> source) {
        this.source = source;
    }

    @Override
    public List<Document> all() {
        List<Document> documents = new ArrayList<>();
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.ID FROM T_DOCUMENT t0")) {
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        documents.add(new DbDocument(source, resultSet.getInt("ID")));
                    }
                }
            }
            return documents;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public Document document(int idDocument) throws Exception {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT t0.ID FROM T_DOCUMENT t0 WHERE ID = ?")) {
                preparedStatement.setInt(1, idDocument);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        return new DbDocument(source, resultSet.getInt("ID"));
                    } else {
                        throw new Exception("Не найден документ с ID: " + idDocument);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public Document create(NewDocument document) throws Exception {
        int idDocument = document.id();

        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO T_DOCUMENT(ID, NAME, DESCRIPTION, ACTIVE, TABLE_NAME, USERNAME, ADD_DT) VALUES(?, ?, ?, ?, ?, ?, ?)"
            )) {
                preparedStatement.setInt(1, idDocument);
                preparedStatement.setString(2, document.name());
                preparedStatement.setString(3, document.description());
                preparedStatement.setBoolean(4, document.active());
                preparedStatement.setString(5, document.tableName());
                preparedStatement.setString(6, document.username());
                preparedStatement.setTimestamp(7, new Timestamp(document.addDt().getTime()));
                preparedStatement.execute();

                Document dbDocument = new DbDocument(source, idDocument);
                dbDocument.createFields(document.fields().all());

                new StatementQuery(new H2CreateTableQueryText(dbDocument), source).execute();

                return dbDocument;
            }
        }

    }

    @Override
    public Document edit(EditDocument document) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Document delete(int idDocument) throws Exception {
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE T_DOCUMENT_FIELD t0 SET t0.ACTIVE = FALSE  WHERE t0.ID = ?;")) {
                preparedStatement.setInt(1, idDocument);
                preparedStatement.execute();
                return document(idDocument);
            }
        }
    }
}
