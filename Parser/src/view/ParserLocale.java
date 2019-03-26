package view;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class {@code ParserLocale} includes
 * methods and fields required for
 * localisation of application
 *
 * @author Alexander Valai
 */

public class ParserLocale {
    private static final String strMsg = "ParserLocale";
    private static Locale loc = Locale.getDefault();
    private static ResourceBundle res = ResourceBundle.getBundle(ParserLocale.strMsg, ParserLocale.loc);

    /**
     * method sets the current locale
     * */

    static void set(Locale loc) {
        ParserLocale.loc = loc;
        res = ResourceBundle.getBundle(ParserLocale.strMsg, ParserLocale.loc);
    }

    static ResourceBundle getBundle() {
        return ParserLocale.res;
    }
    /**
     * method to get value from resource bundle by key
     * */
    public static String getString(String key) {
        return ParserLocale.res.getString(key);
    }

    public static final String token = "token";
    public static final String word = "word";
    public static final String punctuation = "punctuation";
    public static final String sentence = "sentence";
    public static final String paragraph = "paragraph";
    public static final String code = "code";
    public static final String textParagraph = "text";
    public static final String errMessage = "errMessage";
    public static final String errNullText = "nulText";
    public static final String errIllegalArg = "illegalArg";
    public static final String uniqueMessage = "uniqueMessage";
    public static final String lengthMessage1 = "lengthMessage1";
    public static final String lengthMessage2 = "lengthMessage2";
    public static final String narrative = "narrative";
    public static final String incentive = "incentive";
    public static final String interrogative = "interrogative";

}
