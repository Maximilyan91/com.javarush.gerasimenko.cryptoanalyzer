package service.impl;

import model.Languages;
import service.Cryptographer;
import util.CryptoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CryptographerImpl implements Cryptographer {

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

    @Override
    public List<String> bruteForce(Languages lang, String text) {
        List<String> allResults = new ArrayList<>();
        int searchNum = lang.getSymbolsCapacity();

        for (int i = 1; i < searchNum; i++) {
            allResults.add(decrypt(lang, text, i));
        }
        return allResults;
    }
}
