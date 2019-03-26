package controller.parsers;

import model.Code;
import model.Paragraph;
import model.TextParagraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code ParagraphParser}
 * includes methods to parse text in paragraphs
 *
 * @author Alexander Valai
 */

public class ParagraphParser extends Parser {

    public ParagraphParser(Parser p) {
        super(p);
    }

    private static Pattern ParagraphPattern = Pattern.compile(".+");
    private static Pattern TabsSpacesPattern = Pattern.compile("\\t| {2,}");

    @Override
    public void parse(String text) {
        String clearText = clearText(text);
        Matcher matcher = ParagraphPattern.matcher(clearText);
        boolean isCode = false;
        StringBuilder currentCode = new StringBuilder();
        while(matcher.find()) {
            String paragraph = matcher.group();

            if(paragraph.equals("<code>")) {
                if(isCode) {
                    currentCode.append(paragraph);
                    Parser.text.getParagraphs().add(new Code(currentCode.toString()));
                    currentCode = new StringBuilder();
                    isCode = false;
                    continue;
                }
                else {
                    isCode = true;
                }
            }

            if(!isCode)
                Parser.text.getParagraphs().add(new TextParagraph(paragraph));
            else
                currentCode.append(paragraph);
        }
        for(Paragraph p: Parser.text.getParagraphs()) {
            if(p instanceof TextParagraph) {
                nextParser.parse(p.getText());
            }
        }
    }

    private String clearText(String txt) {
        StringBuilder newText = new StringBuilder(txt);
        Matcher matcher = TabsSpacesPattern.matcher(newText);
        while(matcher.find()) {
            newText.delete(matcher.start(), matcher.end());
            newText.insert(matcher.start(), ' ');
            matcher = TabsSpacesPattern.matcher(newText);
        }
        return newText.toString();
    }
}
