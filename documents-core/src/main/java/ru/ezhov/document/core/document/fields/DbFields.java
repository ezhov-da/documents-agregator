package ru.ezhov.document.core.document.fields;

import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbFields implements Fields {

    private int idDocument;
    private Source<DataSource> source;

    public DbFields(int idDocument, Source<DataSource> source) {
        this.idDocument = idDocument;
        this.source = source;
    }

    @Override
    public List<Field> all() {
        List<Field> fields = new ArrayList<>();
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "SELECT\n" +
                                         "  t0.ID\n" +
                                         "FROM T_DOCUMENT_FIELD t0\n" +
                                         "  INNER JOIN T_DOCUMENT_FIELD_TYPE t1 ON t0.ID_FIELD_TYPE = t1.ID WHERE t0.ID_DOCUMENT = ? ORDER BY ORDERR;")) {
                preparedStatement.setInt(1, idDocument);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        fields.add(new DbField(idDocument, resultSet.getInt("ID"), source));
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        return fields;
    }

    @Override
    public Field field(int id) {
        throw new UnsupportedOperationException();
    }
}
