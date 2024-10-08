package model;

import java.util.Arrays;

public enum Languages {

    RUS("Русский",
            new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
                    'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
                    'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й',
                    'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш',
                    'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', '\n', '\t',
                    '\r', ':', '!', '?', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}),

    ENG("Английский (ASCII)",
            new char[]{' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-',
                    '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<',
                    '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                    'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', '{', '|', '}', '~',});

    private final String name;
    private final char[] AllSymbols;

    Languages(String name, char[] allSymbols) {
        AllSymbols = allSymbols;
        this.name = name;
    }

    public char[] getAllSymbols() {
        return AllSymbols;
    }

    public int getSymbolsCapacity() {
        return AllSymbols.length;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", AllSymbols=" + Arrays.toString(AllSymbols) +
                '}';
    }
}
