package view;

import controller.TextAnalyser;
import controller.parsers.ParagraphParser;
import controller.parsers.SentenceParser;
import controller.parsers.TokenParser;
import model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * The class {@code Demonstrator} includes
 * methods to perform tasks with text: reading from file,
 * parsing, building and analysing
 *
 * @author Alexander Valai
 */

public class Demonstrator {
    /**
     * name of input file
     */
    private static final String fileName = "extracut.txt";
    private static ParagraphParser paragraphParser;
    private static String text;
    private static Text txt;
    private static Logger fileLogger = LogManager.getRootLogger();
    private static Logger consoleLogger = Logger.getLogger("logfile");

    public static void main(String[] args) {
        try {
            fileLogger.info("Program execution started");
            paragraphParser = new ParagraphParser(new SentenceParser(new TokenParser(null)));
            readFile();

            fileLogger.info("parsing started");
            parseText();
            fileLogger.warn("parsing finished");

            txt = paragraphParser.getText();
            Locale loc = createLocale(args);
            ParserLocale.set(loc);
        } catch (IOException e) {
            System.err.println("Неверно указано имя файла:");
            consoleLogger.error("Invalid file");
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            consoleLogger.error("Invalid args");
            System.exit(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        fileLogger.debug("Showing of text fragments:");
        showParagraphs();
        showSentences();
        //showTokens();
        System.out.println();
        try {
            consoleLogger.info("Find word feature:");
            findWord();
            consoleLogger.info("Find word with length feature:");
            findWordsByLength(3);
            findWordsByLength(4);
            findWordsByLength(11);
            findWordsByLength(-7);
        } catch (IllegalArgumentException e) {
            consoleLogger.error("Invalid argument(s)");
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    static Locale createLocale(String[] args) throws IllegalArgumentException {
        if (args.length == 2) {
            consoleLogger.info("Locale with args " + args[0] + ' ' + args[1] + " created");
            return new Locale(args[0], args[1]);
        } else {
            throw new IllegalArgumentException(ParserLocale.getString(ParserLocale.errMessage));
        }
    }

    /**
     * method read text from file
     */
    public static void readFile() throws IOException {
        consoleLogger.info("Started reading from file");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null)
            stringBuilder.append(line + "\n");
        text = stringBuilder.toString();
    }

    public static void parseText() {
        consoleLogger.debug("Text is parsing");
        paragraphParser.parse(text);
    }

    public static void showParagraphs() {
        for (Paragraph p : paragraphParser.getText().getParagraphs()) {
            System.out.println(p.toString());
        }
    }

    public static void showSentences() {
        for (Sentence s : paragraphParser.getText().getSentences()) {
            System.out.println(s.toString());
        }
    }

    public static void showTokens() {
        for (Token t : paragraphParser.getText().getTokens()) {
            System.out.println(t.toString());
        }
    }

    /**
     * method for finding unique word in first sentence
     */
    public static void findWord() throws IllegalArgumentException{
        TextAnalyser textAnalyser = new TextAnalyser(txt);
        System.out.println(ParserLocale.getString(ParserLocale.uniqueMessage));
        for (Word word : textAnalyser.findUniqueWord()) {
            System.out.println(word);
        }
        System.out.println();
    }

    /**
     * method for finding words with specified length in interrogative sentences
     */
    public static void findWordsByLength(int length){
        TextAnalyser tn = new TextAnalyser(txt);
        System.out.println(ParserLocale.getString(ParserLocale.lengthMessage1) + length
                + ParserLocale.getString(ParserLocale.lengthMessage2));
        for (Word word : tn.findWordsByLength(length)) {
            System.out.println(word);
        }
        System.out.println();
    }
}
