package be.inniger.tour;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ColourFormatter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final List<String> ANSI_COLOURS = Stream.of(Colour.values()).map(Colour::getCode).collect(toList());

    private final int nrElements;

    private ColourFormatter(int nrElements) {
        this.nrElements = nrElements;
    }

    public static ColourFormatter with(int nrElements) {
        return new ColourFormatter(nrElements);
    }

    public String format(String format, int value) {
        return String.format(format, getColour(value), value, ANSI_RESET);
    }

    private String getColour(int i) {
        int index = i * ANSI_COLOURS.size() / nrElements;

        return ANSI_COLOURS.get(index % ANSI_COLOURS.size());
    }

    private enum Colour {

        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m");

        private final String code;

        Colour(String code) {
            this.code = code;
        }

        private String getCode() {
            return code;
        }
    }
}
