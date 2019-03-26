package XML;

import model.Periodical;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * SAX parser class
 * @author Alexander Valai
 * @version 1.0.0
 */

public class PeriodicalParserSAX {//implements PeriodicalParser {

    /**
     * Parse XML file to periodicals using SAX parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
/*
    @Override
    public Periodical[] parse(String fileName) throws Exception {
        File inputFile = new File(fileName);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
        ArrayList<Periodical> periodicals;
        Handler handler = new Handler(cl);
        saxParser.parse(inputFile, handler);

    }

    /**
     * Tags handler

    private class Handler extends DefaultHandler {

        private String currentElement;
        boolean isTitleSet = false;
        private boolean parsed;

        /**
         * Constructor specifying list to which will be added new cars
         * @param collective list to which will be added new person

        public Handler(Collective collective) {
            this.currentElement = null;

            this.parsed = true;

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
                        currentElement = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String textValue = new String(ch, start, length);
                parsed = true;
            }
        }
    }*/
}