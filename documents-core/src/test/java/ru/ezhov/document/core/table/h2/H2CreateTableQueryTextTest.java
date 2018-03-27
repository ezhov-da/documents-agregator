package ru.ezhov.document.core.table.h2;

import org.junit.Test;
import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.table.QueryText;
import ru.ezhov.document.core.util.text.Text;
import ru.ezhov.document.fakeinterface.AbstractFakeDocument;
import ru.ezhov.document.fakeinterface.AbstractFakeField;

import java.util.Arrays;
import java.util.List;

public class H2CreateTableQueryTextTest {
    @Test
    public void createOk() throws Exception {
        Document document = new AbstractFakeDocument() {
            @Override
            public String tableName() {
                return "TEST_TABLE_NAME_CREATE";
            }

            @Override
            public List<Field> fields() {
                return Arrays.asList(
                        new AbstractFakeField() {
                            @Override
                            public String columnName() {
                                return "TEST_COLUMN_NAME_1";
                            }

                            @Override
                            public FieldType type() {
                                return FieldType.STRING;
                            }

                            @Override
                            public int length() {
                                return 100;
                            }
                        },
                        new AbstractFakeField() {
                            @Override
                            public String columnName() {
                                return "TEST_COLUMN_NAME_2";
                            }

                            @Override
                            public FieldType type() {
                                return FieldType.INTEGER;
                            }
                        });
            }

        };

        QueryText queryText = new H2CreateTableQueryText(document);
        Text text = queryText.text();
        System.out.println(text.asString());
    }

}