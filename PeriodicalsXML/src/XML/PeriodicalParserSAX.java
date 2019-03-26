package XML;

import model.*;
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

public class PeriodicalParserSAX implements PeriodicalParser {

    /**
     * Parse XML file to periodicals using SAX parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */

    @Override
    public Periodical[] parse(String fileName) throws Exception {
        File inputFile = new File(fileName);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
        ArrayList<Periodical> periodicals = new ArrayList<>();
        Handler handler = new Handler(periodicals);
        saxParser.parse(inputFile, handler);
        Periodical[] res = new Periodical[] {};
        return periodicals.toArray(res);
    }

    /**
     * Tags handler
*/
    private class Handler extends DefaultHandler {

        private String currentElement;
        private boolean parsed;
        private ArrayList<Periodical> periodicals;

        private double cost;
        private Period period;
        private String publishingHouse;
        private String website;
        private double averageAttendance;
        private String name;
        private int pagesNumber;
        private int circulation;
        private int adsNumber;
        private long subscribers;

        private String type;

        /**
         * Constructor specifying list to which will be added new cars
         * @param periodicals list to which will be added new periodical
*/
        public Handler(ArrayList<Periodical> periodicals) {
            this.periodicals = periodicals;
            this.parsed = true;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            if(qName.equals("cost") || qName.equals("period") || qName.equals("publishing") || qName.equals("type")
                    || qName.equals("name") || qName.equals("pages") || qName.equals("circulation") || qName.equals("website")
                    || qName.equals("average") || qName.equals("subscribers") || qName.equals("adsnumber")) {
                parsed = false;
            }
            currentElement = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if(qName.equals("periodical")) {
                switch (type) {
                    case "magazine":
                        periodicals.add(new Magazine(cost, period, publishingHouse, name, pagesNumber, circulation, adsNumber));
                        break;

                    case "newspaper":
                        periodicals.add(new Newspaper(cost, period, publishingHouse, name, pagesNumber, circulation, subscribers));
                        break;
                    case "onlineMagazine":
                        periodicals.add(new OnlineMagazine(cost, publishingHouse, website, averageAttendance, adsNumber));
                        break;
                    case "onlineNewspaper":
                        periodicals.add(new OnlineNewspaper(cost, publishingHouse, website, averageAttendance, subscribers));
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String textValue = new String(ch, start, length);
            if(!parsed) {
            switch (currentElement) {
                case "cost":
                    cost = Double.parseDouble(textValue);
                    break;
                case "publishing":
                    publishingHouse = textValue;
                    break;
                case "period":
                    period = Period.valueOf(textValue.toUpperCase());
                    break;
                case "type":
                    type = textValue;
                    break;
                case "name":
                    name = textValue;
                    break;
                case "pages":
                    pagesNumber = Integer.parseInt(textValue);
                    break;
                case "circulation":
                    circulation = Integer.parseInt(textValue);
                    break;
                case "website":
                    website = textValue;
                    break;
                case "average":
                    averageAttendance = Double.parseDouble(textValue);
                    break;
                case "subscribers":
                    subscribers = Integer.parseInt(textValue);
                    break;
                case "adsnumber":
                    adsNumber = Integer.parseInt(textValue);
                    break;
            }
            parsed = true;
            }
        }
    }
}