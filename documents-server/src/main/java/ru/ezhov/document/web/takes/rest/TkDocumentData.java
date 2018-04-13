package ru.ezhov.document.web.takes.rest;

import com.google.gson.GsonBuilder;
import org.takes.Response;
import org.takes.facets.fork.RqRegex;
import org.takes.facets.fork.TkRegex;
import org.takes.rs.RsText;
import ru.ezhov.document.core.document.DocumentData;
import ru.ezhov.document.core.document.Documents;
import ru.ezhov.document.core.inputdoc.Row;

import java.io.IOException;
import java.util.Iterator;

public class TkDocumentData implements TkRegex {
    private Documents documents;

    public TkDocumentData(Documents documents) {
        this.documents = documents;
    }

    @Override
    public Response act(RqRegex rqRegex) throws IOException {
        String id = rqRegex.matcher().group("id");
        if (id == null) {
            throw new UnsupportedOperationException();
        } else {
            try {
                DocumentData data = documents.document(Integer.valueOf(id)).data();

                Iterator<Row> iterator = data.rows();
                while (iterator.hasNext()) {

                }

                return new RsText(new GsonBuilder().create().toJson(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RsText("FUCK");
    }

    private class DocDataView {
        private String[] data;

        public DocDataView(String... data) {
            this.data = data;
        }
    }
}
