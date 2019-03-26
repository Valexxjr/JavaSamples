package model;

import view.ParserLocale;

/**
 * The class {@code Punctuation} describes subclass of Token
 * that represents punctuation sign
 *
 * @author Alexander Valai
 */

public class Punctuation extends Token {
    public Punctuation(String punct) {
        super(punct);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Punctuation guest = (Punctuation) obj;
        return guest.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return 19 * value.hashCode();
    }

    @Override
    public String toString() {
        return ParserLocale.getString(ParserLocale.punctuation) + ": " + value;
    }
}
