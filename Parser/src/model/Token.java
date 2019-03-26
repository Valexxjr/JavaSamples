package model;

import view.ParserLocale;

/**
 * The class {@code Token} describes
 * basical lexeme: word or punctuation sign
 *
 * @author Alexander Valai
 */

public abstract class Token {
    /**
     * The field contains word or punctuation as string
     * */
    protected String value;

    public String getValue() {
        return value;
    }

    Token(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ParserLocale.getString(ParserLocale.token) + ": " + value;
    }
}
