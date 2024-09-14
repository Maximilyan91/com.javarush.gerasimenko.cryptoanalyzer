package service;

import model.Languages;

import java.util.List;

public interface Cryptographer {

    String encrypt(Languages lang, String text, int shift);

    String decrypt(Languages lang, String text, int shift);

    List<String> bruteForce(Languages lang, String text);
}
