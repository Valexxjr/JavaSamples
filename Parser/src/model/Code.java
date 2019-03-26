package model;

import view.ParserLocale;

/**
 * The class {@code Code} describes subclass of Paragraph
 * that represents block of code
 *
 * @author Alexander Valai
 */

public class Code extends Paragraph {
    public Code(String code) {
        super(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Code guest = (Code) obj;
        return guest.text.equals(this.text);
    }

    @Override
    public int hashCode() {
        return 29 * text.hashCode();
    }

    @Override
    public String toString() {
        return ParserLocale.getString(ParserLocale.code) + ": " + text;
    }
}
