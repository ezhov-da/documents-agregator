package ru.ezhov.document.core.document;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Documents {
    List<Document> all() throws Exception;

    Document document(int idDocument) throws Exception;

    Document create(Document document) throws Exception;

    Document edit(Document document) throws Exception;

    Document delete(int idDocument) throws Exception;
}
