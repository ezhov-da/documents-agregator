import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

interface InputDoc {
    void readRow(Callback callback) throws Exception;
}

interface Callback<T> {
    T call(T v);
}

/**
 * Created by ezhov_da on 22.03.2018.
 */
public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        InputDoc inputDoc = new XmlInputDoc(App.class.getResourceAsStream("/doc.xml"));
        Callback<List<Object>> callback = v -> {
            v.forEach(t -> System.out.println(t));
            return v;
        };

        try {
            inputDoc.readRow(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class XmlInputDoc implements InputDoc {

    private final InputStream is;
    private final DefaultHandler defaultHandler;
    private Callback callback;

    public XmlInputDoc(InputStream is) {
        this.is = is;
        this.defaultHandler = new OwnDefaultHandler();
    }

    @Override
    public void readRow(Callback callback) throws Exception {
        this.callback = callback;
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(is, defaultHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class OwnDefaultHandler extends DefaultHandler {
        private boolean startRow;
        private boolean endRow;
        private boolean startColumn;
        private boolean endColumn;
        private String val;

        private List<Object> columns = new ArrayList<>();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("r")) {
                startRow = true;
            } else if (qName.equals("c")) {
                startColumn = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("r")) {
                endRow = true;
            } else if (qName.equals("c")) {
                endColumn = true;
            }

            if (startColumn && endColumn) {
                columns.add(val);
                endColumn = false;
            }

            if (startRow && endRow) {
                callback.call(columns);
                columns.removeAll(columns);
                startRow = false;
                endRow = false;
                startColumn = false;
                endColumn = false;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            char[] chCopy = Arrays.copyOfRange(ch, start, start + length);
            val = new String(chCopy);
        }
    }
}
