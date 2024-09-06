package service;

import model.Languages;

public interface Validator {

    boolean isValidKey(Languages lang, int key);
}
