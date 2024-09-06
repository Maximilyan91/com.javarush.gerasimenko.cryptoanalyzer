package service.impl;

import model.Languages;
import service.Cryptographer;
import service.Validator;

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

    private static int keyCorrect(Languages lang, int shift) {
        int symbolsCapacity = lang.getSymbolsCapacity();

        if (validator.isValidKey(lang, shift)) {
            if (shift < -symbolsCapacity) {
                shift %= -symbolsCapacity;
            }

            if (shift > symbolsCapacity) {
                shift %= symbolsCapacity;
            }
        }
        return shift;
    }
}
