package model;

import view.ParserLocale;

/**
 * The class {@code Token} describes subclass of Token
 * that represents word of text
 *
 * @author Alexander Valai
 */

public class Word extends Token {
    public Word(String word) {
        super(word);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Word guest = (Word) obj;
        return guest.value.equals(this.value);
    }


    @Override
    public int hashCode() {
        return 17 * value.hashCode();
    }

    @Override
    public String toString() {
        return ParserLocale.getString(ParserLocale.word) + ": " + value;
    }
}
