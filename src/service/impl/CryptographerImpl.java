package service.impl;

import model.Languages;
import service.Cryptographer;
import service.Validator;
import util.CryptoUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CryptographerImpl implements Cryptographer {

    private static final Validator validator = new ValidatorImpl();


    @Override
    public String encrypt(Languages lang, String text, int shift) {
        shift = CryptoUtils.keyCorrect(lang, shift);
        char[] textArr = text.toCharArray();
        Map<Character, Character> encryptionMap = CryptoUtils.getEncryptMap(lang, shift);
        StringBuilder builder = new StringBuilder();

        for (char c : textArr) {
            builder.append(encryptionMap.getOrDefault(c, 'ὥ'));
        }
        return builder.toString();
    }
    @Override
    public String decrypt(Languages lang, String text, int shift) {
        return encrypt(lang, text, -shift);
    }
}
