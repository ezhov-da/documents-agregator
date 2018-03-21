package ru.ezhov.document.core.table;

import org.junit.Test;
import ru.ezhov.document.core.FieldType;
import ru.ezhov.document.core.document.*;
import ru.ezhov.document.core.name.ColumnName;
import ru.ezhov.document.core.name.TableName;
import ru.ezhov.document.core.source.H2Source;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.util.db.DbQuery;
import ru.ezhov.document.core.util.db.Query;

public class H2TableTextTest {
    @Test
    public void createOk() throws Exception {
        Source source = new H2Source();
        Documents documents = new DbDocuments(new DbDocumentId(source), source);
        Document document = documents.newDocument("тест иииия" + Math.random(), new TableName(), "ezhov_da");
        document.addField("вау", new ColumnName(), "тестовое описание", FieldType.STRING, 100, Order._05, "ezhov_da");

        Query query = new DbQuery(new H2TableText(document).text(), source);
        query.executeStatement();
    }

}