package model;

import view.ParserLocale;

/**
 * The enum {@code SentenceType}
 * describes 3 types of sentences
 *
 * @author Alexander Valai
 */

public enum SentenceType {
    NARRATIVE {
        public String toString() {
            return ParserLocale.getString(ParserLocale.narrative);
        }
    },
    INTERROGATIVE {
        public String toString() {
            return ParserLocale.getString(ParserLocale.interrogative);
        }
    },
    INCENTIVE {
        public String toString() {
            return ParserLocale.getString(ParserLocale.incentive);
        }
    };
}
