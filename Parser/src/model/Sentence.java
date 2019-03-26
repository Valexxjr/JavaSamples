package model;

import view.ParserLocale;

/**
 * The class {@code Sentence} contains fields
 * representing sentence of text
 *
 * @author Alexander Valai
 */

public class Sentence {
    private SentenceType sentenceType;
    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public SentenceType getSentenceType() {
        return sentenceType;
    }

    public Sentence(String text, SentenceType sentenceType) {
        this.sentenceType = sentenceType;
        this.sentence = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Sentence guest = (Sentence) obj;
        return guest.sentence.equals(this.sentence) && guest.sentenceType == this.sentenceType;
    }

    @Override
    public int hashCode() {
        int result = sentenceType.hashCode();
        result = 31 * result + sentence.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return sentenceType.toString() + " " + ParserLocale.getString(ParserLocale.sentence) + ": " + sentence;
    }
}
