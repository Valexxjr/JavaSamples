package controller.parsers;

import model.Punctuation;
import model.Token;
import model.Word;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code TokenParser}
 * includes methods to parse text into tokens
 *
 * @author Alexander Valai
 */

public class TokenParser extends Parser {

    public TokenParser(Parser p) {
        super(p);
    }

    private static Pattern TokenPattern = Pattern.compile("[a-zA-zА-Яа-я'0-9]+(?=\\s|\\.|\\?|\\!|\\;|\\:])");

    public void parse(String text, ArrayList<Token> src) {
        Matcher matcher = TokenPattern.matcher(text);
        while(matcher.find()) {
            String token = matcher.group();
            if(token.equals(".") || token.equals("?") || token.equals("!") ||
                    token.equals(":")||token.equals(".")) {
                src.add(new Punctuation(token));
            }
            else
                src.add(new Word(token));
        }
    }

    @Override
    public void parse(String text) {
        parse(text, Parser.text.getTokens());
    }
}
