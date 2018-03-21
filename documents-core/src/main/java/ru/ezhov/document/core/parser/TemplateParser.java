package ru.ezhov.document.core.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.ezhov.document.core.document.Document;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public class TemplateParser implements Parser {
    private static final Logger LOG = Logger.getLogger(TemplateParser.class.getName());

    private InputStream src;
    private Document document;

    public TemplateParser(Document document, InputStream src) {
        this.document = document;
        this.src = src;
    }

    @Override
    public void parse() throws Exception {
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        saxParser.parse(src, new DefaultHandler() {
            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
            }

            @Override
            public void endDocument() throws SAXException {
                super.endDocument();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);
            }
        });
    }
}
