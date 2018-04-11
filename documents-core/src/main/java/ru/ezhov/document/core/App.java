package ru.ezhov.document.core;

import ru.ezhov.document.core.document.*;
import ru.ezhov.document.core.document.fields.NewFieldImpl;
import ru.ezhov.document.core.document.fields.NewFieldsImpl;
import ru.ezhov.document.core.inputdoc.DocumentDataValidStringInputDoc;
import ru.ezhov.document.core.inputdoc.InputDoc;
import ru.ezhov.document.core.inputdoc.Row;
import ru.ezhov.document.core.inputdoc.XmlInputDoc;
import ru.ezhov.document.core.name.ColumnName;
import ru.ezhov.document.core.name.TableName;
import ru.ezhov.document.core.source.H2Source;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            addToNewDocumentDocument(19);
            showDataDocument(19);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document createNewDocument() throws Exception {
        Source<DataSource> source = new H2Source();
        Document document = new DbDocuments(source).create(
                new NewDocumentImpl(
                        new DbDocumentId(source),
                        "test" + Math.random(),
                        "просто так",
                        new TableName(),
                        "ezhov_da",
                        new NewFieldsImpl(
                                Arrays.asList(
                                        new NewFieldImpl("Код ГМ", "Просто так", true, true, new ColumnName(), FieldType.STRING, 100, false, Order._00, "ezhov_da"),
                                        new NewFieldImpl("Название ГМ", "Просто так", true, false, new ColumnName(), FieldType.STRING, 100, false, Order._01, "ezhov_da"),
                                        new NewFieldImpl("Кол-во моющих средств", "Просто так", true, false, new ColumnName(), FieldType.INTEGER, 100, true, Order._02, "ezhov_da")
                                )
                        )
                )
        );
        return document;
    }

    private static void addToNewDocumentDocument(int idDocument) throws Exception {
        Source<DataSource> source = new H2Source();
        try (InputDoc inputDoc =
                     new XmlInputDoc(
                             new FileInputStream(
                                     new File("documents-sources-examples/document.xml")
                             )
                     )
        ) {
            new DbDocuments(source).document(idDocument).addData(new DocumentDataValidStringInputDoc(inputDoc));
        }
    }

    private static void showDataDocument(int idDocument) throws Exception {
        Source<DataSource> source = new H2Source();
        Iterator<Row> iterator = new DbDocuments(source).document(idDocument).documentData().rows();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Iterator<Object> it = row.values();
            StringBuilder stringBuilder = new StringBuilder();
            while (it.hasNext()) {
                stringBuilder.append(it.next());
                stringBuilder.append("-");
            }
            System.out.println(stringBuilder.subSequence(0, stringBuilder.toString().length() - 1));
        }
    }
}