package XML;

import model.Periodical;

import javax.xml.parsers.ParserConfigurationException;

public interface PeriodicalParser {
    Periodical[] parse(String source)  throws Exception;
}
