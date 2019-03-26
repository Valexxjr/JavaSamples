package model;

import java.util.ArrayList;

/**
 * The class {@code Text} represents text
 * and contains it's paragraphs, sentences and tokens
 * and methods to process it
 *
 * @author Alexander Valai
 */

public class Text {
    /**
     * list of paragraphs in text
     * */
    private ArrayList<Paragraph> paragraphs;
    /**
     * list of sentences in text
     * */
    private ArrayList<Sentence> sentences;
    /**
     * list of tokens in text
     * */
    private ArrayList<Token> tokens;

    public Text() {
        paragraphs = new ArrayList<>();
        sentences = new ArrayList<>();
        tokens = new ArrayList<>();
    }

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
    /**
     * method to build text from paragraphs
     * */
    public String buildFromParagraphs() {
        StringBuilder textBuilder = new StringBuilder();
        for(Paragraph p: paragraphs) {
            textBuilder.append(p.getText() + "\n");
        }
        return textBuilder.toString();
    }
    /**
     * method to build text from sentences
     * */
    public String buildFromSentences() {
        StringBuilder textBuilder = new StringBuilder();
        for(Sentence s: sentences) {
            textBuilder.append(s.getSentence()).append(" ");
        }
        return textBuilder.toString();
    }
    /**
     * method to build text from tokens
     * */
    public String buildFromTokens() {
        StringBuilder textBuilder = new StringBuilder();
        for(Token t: tokens) {
            textBuilder.append(t.getValue()).append(" ");
        }
        return textBuilder.toString();
    }
}
