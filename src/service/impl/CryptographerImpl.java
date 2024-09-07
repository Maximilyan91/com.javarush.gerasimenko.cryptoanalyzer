package service.impl;

import model.Languages;
import service.Cryptographer;
import service.Validator;

import java.util.HashMap;
import java.util.Map;

public class CryptographerImpl implements Cryptographer {

    private static final Validator validator = new ValidatorImpl();

    public static String encrypt(Languages language, String text, int shift) {
        //TODO разработать логику шифрования текста
        return null;
    }

    /**
     * Adjusts the array shift if the key value is greater
     * the number of all symbols in the language.
     */

    private static int keyCorrect(Languages lang, int value) {
        int symbolsCapacity = lang.getSymbolsCapacity();

        if (validator.isValidKey(lang, value)) {
            if (value < -symbolsCapacity) {
                value %= -symbolsCapacity;
            }

            if (value > symbolsCapacity) {
                value %= symbolsCapacity;
            }
        }
        return value;
    }

    /*
     * Логику метода getEncryptMap решил реализовать самостоятельно,
     * без использования утилитарных методов типа Arrays.copyOf()
     * а также Collections.rotate()
     */

    private static Map<Character, Character> getEncryptMap(Languages lang, int shift) {
        char[] symbols = lang.getAllSymbols();
        Map<Character, Character> encryptMap = new HashMap<>();

        if (shift < 0) {
            shift += symbols.length;
        }

        for (int i = 0; i < symbols.length; i++) {
            int shiftedIndex = i + shift;

            if (shiftedIndex < symbols.length) {
                encryptMap.put(symbols[i], symbols[shiftedIndex]);
            } else {
                encryptMap.put(symbols[i], symbols[shiftedIndex - symbols.length]);
            }
        }
        return encryptMap;
    }
}
