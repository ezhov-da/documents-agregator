package ru.ezhov.document.core.inputdoc;

import ru.ezhov.document.core.document.DocumentData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DocumentDataValidStringInputDoc implements DocumentData {

    private final InputDoc<Row<String>> inputDoc;

    public DocumentDataValidStringInputDoc(InputDoc<Row<String>> inputDoc) {
        this.inputDoc = inputDoc;
    }


    @Override
    public Iterator<Row> rows() throws Exception {
        Iterator<Row<String>> iterator = inputDoc.rows();

        return new Iterator<Row>() {
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
