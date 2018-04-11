package ru.ezhov.document.core.table.h2;

import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.table.SelectTableQueryText;
import ru.ezhov.document.core.util.text.Text;
import ru.ezhov.document.core.util.text.TextOf;

import java.util.List;

public class H2SelectAllTableQueryText implements SelectTableQueryText {

    private final Document document;

    public H2SelectAllTableQueryText(Document document) {
        this.document = document;
    }

    @Override
    public Text text() throws Exception {
        String text = buildQueryText();
        System.out.println(text);
        return new TextOf(text);
    }

    private String buildQueryText() throws Exception {
        String query = "SELECT %1$s FROM %2$s";

        String tableName = document.tableName();
        Fields fields = document.fields();
        StringBuilder stringBuilderColumnName = new StringBuilder();

        List<Field> list = fields.all();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            Field field = list.get(i);
            String columnNameField = field.columnName();
            stringBuilderColumnName.append(columnNameField);
            if (i + 1 < size) {
                stringBuilderColumnName.append(", ");
            }
        }

        return String.format(
                query,
                stringBuilderColumnName.toString(),
                tableName
        );
    }
}
