package ru.ezhov.document.core.table.h2;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.table.CreateTableQueryText;
import ru.ezhov.document.core.util.text.Text;
import ru.ezhov.document.core.util.text.TextOf;

import java.util.List;

public final class H2CreateTableQueryText implements CreateTableQueryText {

    private final Document document;

    public H2CreateTableQueryText(Document document) {
        this.document = document;
    }

    @Override
    public Text text() throws Exception {
        return new TextOf(buildQueryText());
    }

    private String buildQueryText() throws Exception {
        String query = "CREATE TABLE %1$s (%2$s)";
        String tableName = document.tableName();
        Fields fields = document.fields();
        StringBuilder stringBuilder = new StringBuilder();

        List<Field> list = fields.all();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Field field = list.get(i);
            stringBuilder.append(buildColumn(field));
            if (i + 1 < size) {
                stringBuilder.append(", ");
            }
        }

        String queryText = String.format(
                query,
                tableName,
                stringBuilder.toString()
        );

        return queryText;
    }

    private String buildColumn(Field field) {
        String columnPattern = "%1$s %2$s";
        String column;

        FieldType fieldType = field.type();
        switch (fieldType) {
            case STRING:
                column = String.format(columnPattern, field.columnName(), "VARCHAR(" + field.length() + ")");
                break;
            case INTEGER:
                column = String.format(columnPattern, field.columnName(), "INTEGER");
                break;
            default:
                throw new RuntimeException("Неподдерживаемый тип столбца");
        }

        return column;
    }
}
