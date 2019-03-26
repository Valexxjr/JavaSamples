package controller.parsers;

import model.Sentence;
import model.SentenceType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code SentenceParser}
 * includes methods to parse text in sentences
 *
 * @author Alexander Valai
 */

public class SentenceParser extends Parser {
    public SentenceParser(Parser p) {
        super(p);
    }

    private static Pattern SentencePattern = Pattern.compile(".*?[.!?](?=\\s|$)");

    @Override
    public void parse(String text) {
        Matcher matcher = SentencePattern.matcher(text);
        while(matcher.find()) {
            String sentence = matcher.group();
            Sentence newSentence;
            switch(sentence.charAt(sentence.length()-1)) {
                case '.':
                    newSentence = new Sentence(sentence, SentenceType.NARRATIVE);
                    break;
                case '?':
                    newSentence = new Sentence(sentence, SentenceType.INTERROGATIVE);
                    break;
                case '!':
                    newSentence = new Sentence(sentence, SentenceType.INCENTIVE);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            Parser.text.getSentences().add(newSentence);
        }
        for(Sentence s: Parser.text.getSentences())
            nextParser.parse(s.getSentence());
    }
}
