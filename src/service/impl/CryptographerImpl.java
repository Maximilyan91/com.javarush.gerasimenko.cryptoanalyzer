package service.impl;

import model.Languages;
import service.Cryptographer;
import service.Validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CryptographerImpl implements Cryptographer {

    private static final Validator validator = new ValidatorImpl();

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

    @Override
    public String encrypt(Languages lang, String text, int shift) {
        shift = keyCorrect(lang, shift);
        char[] textArr = text.toCharArray();
        char[] encryptedArr = new char[textArr.length];
        Map<Character, Character> encryptionMap = getEncryptMap(lang, shift);

        for (int i = 0; i < textArr.length; i++) {
            encryptedArr[i] = encryptionMap.getOrDefault(textArr[i], '`');
        }
        return Arrays.toString(encryptedArr);
    }

    @Override
    public String decrypt(Languages lang, String text, int shift) {
        return encrypt(lang, text, -shift);
    }
}
