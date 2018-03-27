package ru.ezhov.document.core.document;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Documents {
    List<Document> all();

    Document newDoc(DocumentId documentId, Document document);

    Document document(int idDocument);

    Document edit(Document document);

    Document delete(int idDocument);
}
