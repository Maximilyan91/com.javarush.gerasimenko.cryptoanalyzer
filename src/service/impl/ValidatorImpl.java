package service.impl;

import service.Validator;

public class ValidatorImpl implements Validator {

    public static boolean isValidKey(int key) {
        return key != 0;
    }
}
