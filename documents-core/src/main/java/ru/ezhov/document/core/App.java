package ru.ezhov.document.core;

import ru.ezhov.document.core.document.*;
import ru.ezhov.document.core.document.fields.NewField;
import ru.ezhov.document.core.document.fields.NewFields;
import ru.ezhov.document.core.inputdoc.DocumentDataValidStringInputDoc;
import ru.ezhov.document.core.inputdoc.InputDoc;
import ru.ezhov.document.core.inputdoc.XmlInputDoc;
import ru.ezhov.document.core.name.ColumnName;
import ru.ezhov.document.core.name.TableName;
import ru.ezhov.document.core.source.H2Source;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.h2.H2CreateTableQueryText;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Source<DataSource> source = new H2Source();

        try {
            Document document = new DbDocuments(source).create(
                    new NewDocument(
                            new DbDocumentId(source),
                            "test" + Math.random(),
                            "просто так",
                            new TableName(),
                            "ezhov_da",
                            new NewFields(
                                    Arrays.asList(
                                            new NewField("Код ГМ", "Просто так", true, true, new ColumnName(), FieldType.STRING, 100, false, Order._00, "ezhov_da"),
                                            new NewField("Название ГМ", "Просто так", true, false, new ColumnName(), FieldType.STRING, 100, false, Order._01, "ezhov_da"),
                                            new NewField("Кол-во моющих средств", "Просто так", true, false, new ColumnName(), FieldType.INTEGER, 100, true, Order._02, "ezhov_da")
                                    )
                            )
                    )
            );

            try (InputDoc inputDoc =
                         new XmlInputDoc(
                                 new FileInputStream(
                                         new File("documents-sources-examples/document.xml")
                                 )
                         )
            ) {
                document.add(new DocumentDataValidStringInputDoc(inputDoc));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}