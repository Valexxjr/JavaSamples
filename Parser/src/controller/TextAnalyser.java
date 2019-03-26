package controller;

import controller.parsers.TokenParser;
import model.*;
import view.ParserLocale;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class {@code TextAnalyser} includes
 * methods and fields required for
 * performing analytical tasks with text
 *
 * @author Alexander Valai
 */

public class TextAnalyser {
    /**
     * text to analyse
     * */
    private Text text;

    public TextAnalyser(Text txt) throws IllegalArgumentException{
        if(txt == null) {
            throw new IllegalArgumentException(ParserLocale.getString(ParserLocale.errNullText));
        }
        this.text = txt;
    }
    /**
     * method for finding of unique word
     * */
    public ArrayList<Word> findUniqueWord() {
        ArrayList<Sentence> sentences = new ArrayList<>(text.getSentences());
        TokenParser tp = new TokenParser(null);

        ArrayList<Token> firstSentence = new ArrayList<>();
        tp.parse(sentences.remove(0).getSentence(), firstSentence);
        HashSet<Token> allTokensSet = new HashSet<>();
        ArrayList<Token> allTokensList = new ArrayList<>();

        for(Sentence sentence: sentences) {
            tp.parse(sentence.getSentence(), allTokensList);
        }

        for(Token token: allTokensList) {
            if(token instanceof Word) {
                allTokensSet.add(token);
            }
        }

        ArrayList<Word> uniqueWords = new ArrayList<>();

        for(Token token: firstSentence) {
            if(token instanceof Word) {
                if(allTokensSet.add(token)) {
                    uniqueWords.add((Word) token);
                }
            }
        }

        return uniqueWords;
    }
    /**
     * method for finding words with length specified
     * */
    public ArrayList<Word> findWordsByLength(int length) throws IllegalArgumentException {
        if(length <= 0) {
            throw new IllegalArgumentException(ParserLocale.getString(ParserLocale.errIllegalArg));
        }
        TokenParser tokenParser = new TokenParser(null);
        ArrayList<Word> words = new ArrayList<>();

        for(Sentence sentence: text.getSentences()) {
            if(sentence.getSentenceType() == SentenceType.INTERROGATIVE) {
                ArrayList<Token> currentSentenceTokens = new ArrayList<>();
                tokenParser.parse(sentence.getSentence(), currentSentenceTokens);

                for(Token token: currentSentenceTokens) {
                    if(token instanceof Word && token.getValue().length() == length)
                        words.add((Word) token);
                }
            }
        }

        return words;
    }
}
