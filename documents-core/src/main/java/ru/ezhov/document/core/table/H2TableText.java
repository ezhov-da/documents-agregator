package ru.ezhov.document.core.table;

import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.Field;
import ru.ezhov.document.core.util.text.Text;
import ru.ezhov.document.core.util.text.TextOf;

import java.util.List;

public final class H2TableText implements TableText {

    private final Document document;

    public H2TableText(Document document) {
        this.document = document;
    }

    @Override
    public Text text() {
        return new TextOf(buildQueryText());
    }

    private String buildQueryText() {
        String head = "CREATE TABLE %1$s (\n";
        String end = ")";
        String tableName = document.tableName();
        List<Field> fields = document.fields();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format(head, tableName));
        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Field field = fields.get(i);
            stringBuilder.append(buildColumn(field));
            if (i + 1 < size) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(end);
        return stringBuilder.toString();
    }

    private String buildColumn(Field field) {
        String columnPattern = "\t%1$s %2$s";
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
