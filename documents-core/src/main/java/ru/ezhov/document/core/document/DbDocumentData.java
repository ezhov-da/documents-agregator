package ru.ezhov.document.core.document;

import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.inputdoc.Row;
import ru.ezhov.document.core.inputdoc.ValidInputDoc;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.InsertTableQueryText;
import ru.ezhov.document.core.util.text.Text;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class DbDocumentData implements DocumentData {

    private final Document document;
    private final ValidInputDoc<Object> validInputDoc;
    private final InsertTableQueryText insertTableText;
    private Source<DataSource> source;

    public DbDocumentData(
            Document document,
            ValidInputDoc<Object> validInputDoc,
            InsertTableQueryText insertTableText,
            Source<DataSource> source
    ) {
        this.document = document;
        this.validInputDoc = validInputDoc;
        this.insertTableText = insertTableText;
        this.source = source;
    }

    @Override
    public void put() {
        int idDocument = document.id();
        List<Field> fields = document.fields();
        Text queryInsertText = insertTableText.text();

        Connection connection = null;
        try {
            connection = source.get().getConnection();
            connection.setAutoCommit(false);

            validInputDoc.valid();

            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(queryInsertText.asString())) {

                Iterator<Row<Object>> rowIterator = validInputDoc.valid();
                while (rowIterator.hasNext()) {
                    Row<Object> objectRow = rowIterator.next();
                    Iterator<Object> iterator = objectRow.values();
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
        } catch (Exception ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Ошибка при внесении данных в БД", ex);
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
}
