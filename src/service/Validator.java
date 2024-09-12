package service;

import model.Languages;

import java.nio.file.Path;

public interface Validator {

    boolean isValidKey(Languages lang, int key);

    boolean isValidString(String s);

    boolean isValidPath(Path path);

    boolean isValidEncrypt(String encryptedString);
}
