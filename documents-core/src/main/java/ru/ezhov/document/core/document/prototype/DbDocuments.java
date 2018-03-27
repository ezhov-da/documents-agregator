package ru.ezhov.document.core.document.prototype;

import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.DocumentId;
import ru.ezhov.document.core.document.Documents;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;
import java.util.List;

public class DbDocuments implements Documents {

    private Source<DataSource> source;

    public DbDocuments(Source<DataSource> source) {
        this.source = source;
    }

    @Override
    public List<Document> all() {
        return null;
    }

    @Override
    public Document newDoc(DocumentId documentId) {
        return null;
    }

    @Override
    public Document document(int idDocument) {
        return null;
    }

    @Override
    public Document edit(Document document) {
        return null;
    }

    @Override
    public Document delete(int idDocument) {
        return null;
    }
}
