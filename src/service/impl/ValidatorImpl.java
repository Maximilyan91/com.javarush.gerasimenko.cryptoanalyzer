package service.impl;

import exception.ZeroKeyException;
import model.Languages;
import service.Validator;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidatorImpl implements Validator {

    @Override
    public boolean isValidKey(Languages lang, int key) {
        if (Math.abs(key) % lang.getSymbolsCapacity() == 0) {
            throw new ZeroKeyException("сдвиг равен нулю или количеству символов в языке");
        } else {
            return true;
        }
    }

    @Override
    public boolean isValidString(String s) {
        return s != null || !s.isEmpty() || !s.isBlank();
    }

    @Override
    public boolean isValidFIle(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    public boolean isValidEncrypt(String original, String encrypted) {
        if (original.length() == encrypted.length()) {
            return !encrypted.contains("ὥ");
        } else {
            return false;
        }
    }
}
