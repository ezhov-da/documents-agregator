package ru.ezhov.document.web.takes.rest;

import com.google.gson.GsonBuilder;
import org.takes.Response;
import org.takes.facets.fork.RqRegex;
import org.takes.facets.fork.TkRegex;
import org.takes.rs.RsText;
import ru.ezhov.document.core.document.Documents;
import ru.ezhov.document.core.document.fields.Field;
import ru.ezhov.document.core.document.fields.Fields;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TkDocumentFields implements TkRegex {
    private Documents documents;

    public TkDocumentFields(Documents documents) {
        this.documents = documents;
    }

    @Override
    public Response act(RqRegex rqRegex) throws IOException {
        String id = rqRegex.matcher().group("id");
        if (id == null) {
            throw new UnsupportedOperationException();
        } else {
            try {
                Fields fields = documents.document(Integer.valueOf(id)).fields();
                List<Field> fieldList = fields.all();
                List<DocinfoView> views = new ArrayList<>();
                for (Field field : fieldList) {
                    views.add(new DocinfoView(field.id(), field.name()));
                }
                return new RsText(new GsonBuilder().create().toJson(views));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RsText("FUCK");
    }


    private class DocinfoView {
        private int id;
        private String name;

        public DocinfoView(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
