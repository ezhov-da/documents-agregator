package ru.ezhov.document.web;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;
import ru.ezhov.document.core.document.DbDocuments;
import ru.ezhov.document.core.document.Documents;
import ru.ezhov.document.core.source.H2Source;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.web.takes.TkIndex;
import ru.ezhov.document.web.takes.rest.TkDocumentData;
import ru.ezhov.document.web.takes.rest.TkDocuments;

import javax.sql.DataSource;

public final class App {
    public static void main(final String... args) throws Exception {
        Source<DataSource> source = new H2Source();
        Documents documents = new DbDocuments(source);

        /*
        Информация, которая понадобиться на клиенте:
            Просмотр:
                - Список всех активных документов
                - Информация по активным полям
                - Имена активных полей документа (просто список для динамического отображения)
                - Данные по активным полям

            Редактирование:
                - Список всех документов с идентификацией активный/неактивный
                - При выборе документа получать информацию по всем полям документа

            Создание/изменение данных документа:
                - Внесение новых данных
                - Редактирование данных
                - Удаление

        */

        new FtBasic(new TkFork(
                new FkRegex("/", new TkIndex(source)),
                new FkRegex("/documents/rest/view/active/info", new TkDocuments(documents)),
                new FkRegex("/documents/(?<id>\\d+)/rest/view/fields/active/info", new TkDocumentData(documents)),
                new FkRegex("/documents/(?<id>\\d+)/rest/view/fields/active/names", new TkDocumentData(documents)),
                new FkRegex("/documents/(?<id>\\d+)/rest/view/data/active", new TkDocumentData(documents)),

                new FkRegex("/documents/rest/edit/all/info", new TkDocuments(documents)),
                new FkRegex("/documents/(?<id>\\d+)/rest/edit/fields/all/info", new TkDocumentData(documents)),

                new FkRegex("/documents/(?<id>\\d+)/action/new", new TkDocumentData(documents)),
                new FkRegex("/documents/(?<id>\\d+)/action/edit", new TkDocumentData(documents)),
                new FkRegex("/documents/(?<id>\\d+)/action/delete", new TkDocumentData(documents))
        )
                , 8080
        ).start(Exit.NEVER);
    }
}
