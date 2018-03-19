package ru.ezhov.template.core.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.ezhov.template.core.template.Template;

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
    private Template template;

    public TemplateParser(Template template, InputStream src) {
        this.template = template;
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
