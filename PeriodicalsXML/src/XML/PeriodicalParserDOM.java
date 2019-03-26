package XML;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * XML DOM parser class
 * @author Alexander Valai
 * @version 1.0.0
 */

public class PeriodicalParserDOM implements PeriodicalParser{
    @Override
    public Periodical[] parse(String source) throws Exception{
        File inputFile = new File(source);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("periodical");
        ArrayList<Periodical> periodicals = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); ++i) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Double cost = Double.parseDouble(element.getElementsByTagName("cost").item(0).getTextContent());
                Period period = Period.valueOf(element.getElementsByTagName("period").item(0).getTextContent().toUpperCase());
                String publishing = element.getElementsByTagName("publishing").item(0).getTextContent().toUpperCase();
                String type = element.getElementsByTagName("type").item(0).getTextContent();
                switch (type){
                    case "magazine": {
                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        int pages = Integer.parseInt(element.getElementsByTagName("pages").item(0).getTextContent());
                        int circulation = Integer.parseInt(element.getElementsByTagName("circulation").item(0).getTextContent());
                        int adsnumber = Integer.parseInt(element.getElementsByTagName("adsnumber").item(0).getTextContent());
                        periodicals.add(new Magazine(cost, period, publishing, name, pages, circulation, adsnumber));
                        break;
                    }
                    case "newspaper": {
                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        int pages = Integer.parseInt(element.getElementsByTagName("pages").item(0).getTextContent());
                        int circulation = Integer.parseInt(element.getElementsByTagName("circulation").item(0).getTextContent());
                        int subscribers = Integer.parseInt(element.getElementsByTagName("subscribers").item(0).getTextContent());
                        periodicals.add(new Newspaper(cost, period, publishing, name, pages, circulation, subscribers));
                        break;
                    }
                    case "onlineMagazine": {
                        String website = element.getElementsByTagName("website").item(0).getTextContent();
                        double avAttendance = Double.parseDouble(element.getElementsByTagName("average").item(0).getTextContent());
                        int adsnumber = Integer.parseInt(element.getElementsByTagName("adsnumber").item(0).getTextContent());
                        periodicals.add(new OnlineMagazine(cost, publishing, website, avAttendance, adsnumber));
                        break;
                    }
                    case "onlineNewspaper": {
                        String website = element.getElementsByTagName("website").item(0).getTextContent();
                        double avAttendance = Double.parseDouble(element.getElementsByTagName("average").item(0).getTextContent());
                        int subscribers = Integer.parseInt(element.getElementsByTagName("subscribers").item(0).getTextContent());
                        periodicals.add(new OnlineNewspaper(cost, publishing, website, avAttendance, subscribers));
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        Periodical[] result = new Periodical[] {};
        return periodicals.toArray(result);
    }
}
