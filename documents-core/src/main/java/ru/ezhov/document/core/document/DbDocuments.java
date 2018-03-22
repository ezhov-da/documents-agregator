package ru.ezhov.document.core.document;

import ru.ezhov.document.core.name.Name;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.CreateTableQueryText;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbDocuments implements Documents {

    private Source<DataSource> source;
    private DocumentId templateId;
    private CreateTableQueryText createTableText;

    public DbDocuments(final DocumentId templateId, final Source<DataSource> source) {
        this.source = source;
        this.templateId = templateId;
        this.createTableText = createTableText;
    }

    @Override
    public List<Document> all() {
        List<Document> documents = new ArrayList<>();
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ID FROM T_DOCUMENT")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            documents.add(new DbDocument(resultSet.getInt(1), source));
                        }

                        if (documents.isEmpty()) {
                            return Collections.EMPTY_LIST;
                        } else {
                            return documents;

                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Document newDocument(String name, Name tableName, String username) {
        int id = templateId.get();

        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "INSERT INTO T_DOCUMENT (\n" +
                                             "ID, NAME, TABLE_NAME, USERNAME" +
                                             ") VALUES (\n" +
                                             " ?, ?, ?, ?)"
                             )) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, tableName.get());
                    preparedStatement.setString(4, username);

                    preparedStatement.execute();

                    return document(id);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Document document(int idTemplate) {
        return new DbDocument(idTemplate, source);
    }
}
