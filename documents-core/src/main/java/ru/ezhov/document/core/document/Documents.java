package ru.ezhov.document.core.document;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Documents {
    List<Document> all() throws Exception;

    Document document(int idDocument) throws Exception;

    Document create(NewDocument document) throws Exception;

    Document edit(EditDocument document) throws Exception;

    Document delete(int idDocument) throws Exception;
}
