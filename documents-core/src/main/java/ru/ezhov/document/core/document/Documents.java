package ru.ezhov.document.core.document;

import ru.ezhov.document.core.name.Name;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Documents {
    List<Document> all();

    Document newDocument(String name, Name tableName, String username);

    Document document(int idDocument);

    //TODO: обновление шаблона
    //TODO: удаление шаблона
}
