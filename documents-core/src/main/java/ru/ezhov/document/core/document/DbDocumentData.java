package ru.ezhov.document.core.document;


import ru.ezhov.document.core.inputdoc.ObjectRow;
import ru.ezhov.document.core.inputdoc.Row;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.SelectTableQueryText;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbDocumentData implements DocumentData {

    private Source<DataSource> source;
    private SelectTableQueryText selectTableQueryText;

    public DbDocumentData(Source<DataSource> source, SelectTableQueryText selectTableQueryText) {
        this.source = source;
        this.selectTableQueryText = selectTableQueryText;
    }

    @Override
    public Iterator<Row> rows() throws Exception {
        List<Row> rows = new ArrayList<>();
        try (Connection connection = source.get().getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(selectTableQueryText.text().asString())) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
                    int columnCount = resultSetMetaData.getColumnCount();
                    while (resultSet.next()) {
                        List<Object> row = new ArrayList<>();
                        for (int c = 1; c <= columnCount; c++) {
                            row.add(resultSet.getObject(c));
                        }
                        rows.add(new ObjectRow(row));
                    }
                }
            }
        }
        return rows.iterator();
    }
}
