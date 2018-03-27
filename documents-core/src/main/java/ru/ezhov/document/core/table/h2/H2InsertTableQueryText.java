package ru.ezhov.document.core.table.h2;

import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.table.InsertTableQueryText;
import ru.ezhov.document.core.util.text.Text;
import ru.ezhov.document.core.util.text.TextOf;

import java.util.List;

public final class H2InsertTableQueryText implements InsertTableQueryText {

    private final Document document;

    public H2InsertTableQueryText(Document document) {
        this.document = document;
    }

    @Override
    public Text text() {
        return new TextOf(buildQueryText());
    }

    private String buildQueryText() {
        String query = "INSERT INTO %1$s (%2$s) VALUES (%3$s)";

        String tableName = document.tableName();
        List<Field> fields = document.fields();
        StringBuilder stringBuilderColumnName = new StringBuilder();
        StringBuilder stringBuilderChar = new StringBuilder();

        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Field field = fields.get(i);
            String columnNameField = field.columnName();
            stringBuilderColumnName.append(columnNameField);
            stringBuilderChar.append("?");
            if (i + 1 < size) {
                stringBuilderColumnName.append(", ");
                stringBuilderChar.append(", ");
            }
        }

        String queryText = String.format(
                query,
                tableName,
                stringBuilderColumnName.toString(),
                stringBuilderChar.toString()
        );

        return queryText;
    }
}
