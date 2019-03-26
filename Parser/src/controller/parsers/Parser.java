package controller.parsers;

import model.Text;

/**
 * The abstract class {@code Parser} contains fields for description
 * of standard properties of parsing classes
 *
 * @author Alexander Valai
 */

public abstract class Parser {
    protected Parser nextParser;
    protected static Text text = new Text();

    public Text getText() {
        return text;
    }

    Parser(Parser p) {
        nextParser = p;
    }
    /**
     * method that parses text into parts
     * */
    public abstract void parse(String text);

}
