package ru.ezhov.document.core.inputdoc;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class XmlInputDoc implements InputDoc<Row<String>> {
    private final InputStream is;

    private boolean startRow;
    private boolean endRow;
    private boolean startColumn;
    private boolean endColumn;
    private String val;

    private List<String> columns = new ArrayList<>();

    public XmlInputDoc(InputStream is) {
        this.is = is;
    }

    public Iterator<Row<String>> rows() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            return new Iterator() {
                @Override
                public boolean hasNext() {
                    try {
                        boolean hasNext = false;
                        boolean breakFlag = false;
                        try {
                            int event = reader.getEventType();
                            columns = new ArrayList<>();
                            while (true) {
                                String elementName;
                                switch (event) {
                                    case XMLStreamConstants.START_ELEMENT:
                                        elementName = reader.getLocalName();
                                        if ("r".equals(elementName)) {
                                            startRow = true;
                                        } else if ("c".equals(elementName)) {
                                            startColumn = true;
                                        }
                                        break;
                                    case XMLStreamConstants.END_ELEMENT:
                                        elementName = reader.getLocalName();
                                        if ("r".equals(elementName)) {
                                            endRow = true;
                                        } else if ("c".equals(elementName)) {
                                            endColumn = true;
                                        }
                                        if (startColumn && endColumn) {
                                            columns.add(val);
                                            endColumn = false;
                                        }
                                        if (startRow && endRow) {
                                            startRow = false;
                                            endRow = false;
                                            startColumn = false;
                                            endColumn = false;
                                            hasNext = true;
                                            breakFlag = true;
                                        }
                                        break;

                                    case XMLStreamConstants.CHARACTERS:
                                        if (reader.isWhiteSpace())
                                            break;
                                        val = reader.getText();
                                        break;
                                }
                                if (!reader.hasNext()) {
                                    return hasNext;
                                }
                                event = reader.next();
                                if (breakFlag) {
                                    //проверка наполненности строки
                                    break;
                                }

                            }
                            return hasNext;
                        } finally {
                            if (!hasNext) {
                                reader.close();
                            }
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public Row<String> next() {
                    return new StringRow(columns);
                }
            };
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
