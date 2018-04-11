package ru.ezhov.document.core.table.h2;

import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
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
    public Text text() throws Exception {
        String text = buildQueryText();
        System.out.println(text);
        return new TextOf(text);
    }

    private String buildQueryText() throws Exception {
        String query = "INSERT INTO %1$s (%2$s) VALUES (%3$s)";

        String tableName = document.tableName();
        Fields fields = document.fields();
        StringBuilder stringBuilderColumnName = new StringBuilder();
        StringBuilder stringBuilderChar = new StringBuilder();

        List<Field> list = fields.all();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            Field field = list.get(i);
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
