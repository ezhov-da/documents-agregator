package ru.ezhov.document.core;

import ru.ezhov.document.core.document.*;
import ru.ezhov.document.core.document.fields.Fields;
import ru.ezhov.document.core.inputdoc.InputDoc;
import ru.ezhov.document.core.inputdoc.ValidStringInputDoc;
import ru.ezhov.document.core.inputdoc.XmlInputDoc;
import ru.ezhov.document.core.name.ColumnName;
import ru.ezhov.document.core.name.TableName;
import ru.ezhov.document.core.source.H2Source;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.core.table.h2.H2CreateTableQueryText;
import ru.ezhov.document.core.table.h2.H2InsertTableQueryText;
import ru.ezhov.document.core.util.db.StatementQuery;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {

            Document document = new DocumentTest();
            Fields fields = document.fields();
            fields.










            //Получение источника
            Source source = new H2Source();

            //Получение хранилища документов
            Documents documents = new DbDocuments(new DbDocumentId(source), source);

            //Создание нового документа
            Document document = documents.newDocument("рандомное имя: " + Math.random(), new TableName(), "ezhov_da");

            //Добавление полей
            document.addField("Код", new ColumnName(), "-", FieldType.STRING, 100, Order._00, "ezhov_da");
            document.addField("Название", new ColumnName(), "+", FieldType.STRING, 100, Order._01, "ezhov_da");
            document.addField("Количество", new ColumnName(), "*", FieldType.INTEGER, 100, Order._02, "ezhov_da");

            //Создание хранилища данных для сгенерированного документа
            new StatementQuery(new H2CreateTableQueryText(document), source).execute();


            //Получение входного документа
            try (InputDoc inputDoc =
                         new XmlInputDoc(
                                 new FileInputStream(
                                         new File("documents-sources-examples/document.xml")
                                 )
                         )
            ) {
                //полная обработка
                new DbDocumentData(
                        document,
                        new ValidStringInputDoc(
                                document,
                                inputDoc
                        ),
                        new H2InsertTableQueryText(document),

                        source
                ).

                        put();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
