package ru.ezhov.document.core.inputdoc;

import ru.ezhov.document.core.document.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValidStringInputDoc implements ValidInputDoc<Object> {

    private final Document document;
    private final InputDoc<Row<String>> inputDoc;

    public ValidStringInputDoc(Document document, InputDoc<Row<String>> inputDoc) {
        this.document = document;
        this.inputDoc = inputDoc;
    }

    @Override
    public Iterator<Row<Object>> valid() throws Exception {
        Iterator<Row<String>> iterator = inputDoc.rows();

        return new Iterator<Row<Object>>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Row<Object> next() {
                Row<String> row = iterator.next();
                Iterator<String> stringIterator = row.values();
                List<Object> objectList = new ArrayList<>();
                while (stringIterator.hasNext()) {
                    objectList.add(stringIterator.next());
                }
                return new ObjectRow(objectList.iterator());
            }
        };
    }
}
