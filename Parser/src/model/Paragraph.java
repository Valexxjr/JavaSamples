package model;

import view.ParserLocale;

/**
 * The abstract class {@code Paragraph} contains fields for description
 * of standard properties of paragraphs
 *
 * @author Alexander Valai
 */

public abstract class Paragraph {
    /**
     * The field contains text as string
     * */
    String text;

    Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return ParserLocale.getString(ParserLocale.paragraph) + ": " + text;
    }
}
