package ru.ezhov.reader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

interface InputDoc<T> {
    Iterator<T> iterator() throws XMLStreamException;
}

/**
 * Created by ezhov_da on 22.03.2018.
 */
public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        InputDoc inputDoc = new XmlInputDoc(App.class.getResourceAsStream("/doc.xml"));
        try {
            Iterator<List<String>> iterator = inputDoc.iterator();
            while (iterator.hasNext()) {
                List<String> strings = iterator.next();
                strings.forEach(v -> System.out.println(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class XmlInputDoc implements InputDoc<List<String>> {
    private final InputStream is;

    private boolean startRow;
    private boolean endRow;
    private boolean startColumn;
    private boolean endColumn;
    private String val;

    private List<Object> columns = new ArrayList<>();

    public XmlInputDoc(InputStream is) {
        this.is = is;
    }

    public Iterator iterator() throws XMLStreamException {
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
            public Object next() {
                return columns;
            }
        };
    }
}
