package XML;

import model.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * StAX parser class
 * @author Alexander Valai
 * @version 1.0.0
 */
public class PeriodicalParserStAX implements PeriodicalParser{

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

    public PeriodicalParserStAX(){

    }

    /**
     * Parse XML file to Periodical array using StAX parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed periodical array
     * @throws Exception if some error occurred while parsing XML file
     * */
    @Override
    public Periodical[] parse(String fileName) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));
        ArrayList<Periodical> periodicals = new ArrayList<>();
        String currentElement = new String();
        boolean parsed;
        parsed = true;

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            String qName;

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    qName = startElement.getName().getLocalPart();
                    if(qName.equals("cost") || qName.equals("period") || qName.equals("publishing") || qName.equals("type")
                            || qName.equals("name") || qName.equals("pages") || qName.equals("circulation") || qName.equals("website")
                            || qName.equals("average") || qName.equals("subscribers") || qName.equals("adsnumber")) {
                        parsed = false;
                    }
                    currentElement = qName;
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String textValue = event.asCharacters().getData();
                    if(!parsed) {
                        switch (currentElement) {
                            case "cost":
                                cost = Double.parseDouble(textValue);
                                break;
                            case "period":
                                period = Period.valueOf(textValue.toUpperCase());
                                break;
                            case "publishing":
                                publishingHouse = textValue;
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
                            case "average":
                                averageAttendance = Double.parseDouble(textValue);
                                break;
                            case "website":
                                website = textValue;
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
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    qName = endElement.getName().getLocalPart();
                    if(qName.equals("periodical")) {
                        switch (type) {
                            case "magazine":
                                periodicals.add(new Magazine(cost, period, publishingHouse, name, pagesNumber, circulation, adsNumber));
                                break;
                            case "onlineMagazine":
                                periodicals.add(new OnlineMagazine(cost, publishingHouse, website, averageAttendance, adsNumber));
                                break;
                            case "newspaper":
                                periodicals.add(new Newspaper(cost, period, publishingHouse, name, pagesNumber, circulation, subscribers));
                                break;
                            case "onlineNewspaper":
                                periodicals.add(new OnlineNewspaper(cost, publishingHouse, website, averageAttendance, subscribers));
                                break;
                            default:
                                break;
                        }
                    }
            }
        }

        Periodical[] res = new Periodical[]{};

        return periodicals.toArray(res);
    }
}