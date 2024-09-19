package util;

import model.Languages;
import service.Validator;
import service.impl.ValidatorImpl;

import java.util.HashMap;
import java.util.Map;

public class CryptoUtils {

    private static final Validator validator = new ValidatorImpl();

    /**
     * Регулирует сдвиг массива,
     * если значение ключа больше чем кол-во символов в языке.
     */

    public static int keyCorrect(Languages lang, int value) {
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

    public static Map<Character, Character> getEncryptMap(Languages lang, int shift) {
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
