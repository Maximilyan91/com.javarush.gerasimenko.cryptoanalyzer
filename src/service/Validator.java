package service;

import model.Languages;

import java.nio.file.Path;

public interface Validator {

    boolean isValidKey(Languages lang, int key);

    boolean isValidString(String s);

    boolean isValidFIle(Path path);

    boolean isValidEncrypt(String text, String encrypted);
}
