package ru.ezhov.document.web.takes.rest;

import com.google.gson.GsonBuilder;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsText;
import ru.ezhov.document.core.document.Document;
import ru.ezhov.document.core.document.Documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TkDocuments implements Take {
    private Documents documents;

    public TkDocuments(Documents documents) {
        this.documents = documents;
    }

    @Override
    public Response act(Request request) throws IOException {
        try {
            List<Document> docs = documents.all();

            List<DocumentView> views = new ArrayList<>();
            for (Document document : docs) {
                views.add(new DocumentView(document.id(), document.name()));
            }
            return new RsText(new GsonBuilder().create().toJson(views));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RsText("FUCK");

    }

    private class DocumentView {
        private int id;
        private String name;

        public DocumentView(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
