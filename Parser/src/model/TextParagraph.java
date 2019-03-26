package model;

import view.ParserLocale;

/**
 * The class {@code TextParagraph} contains fields for description
 * of standard properties of paragraphs
 *
 * @author Alexander Valai
 */

public class TextParagraph extends Paragraph {
    public TextParagraph(String text) {
        super(text);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        TextParagraph guest = (TextParagraph) obj;
        return guest.text.equals(this.text);
    }

    @Override
    public int hashCode() {
        return 21 * text.hashCode();
    }

    @Override
    public String toString() {
        return ParserLocale.getString(ParserLocale.textParagraph) + ": " + text;
    }
}
