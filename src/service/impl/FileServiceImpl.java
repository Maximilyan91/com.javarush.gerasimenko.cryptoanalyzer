package service.impl;

import service.FileService;
import service.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

   private static final Validator validator = new ValidatorImpl();

    //TODO добавить проверки, валидацию пути и текста в оба метода

    @Override
    public String getStringFromFile(Path path) {
        String text;
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e + " - Неудачная попытка чтения файла");
        }

        if (!validator.isValidString(text)) {
            throw new IllegalArgumentException(text);
        } else {
            return text;
        }
    }

    @Override
    public void writeFile(String pathName, String text) {


        if (!validator.isValidString(text)) {
            throw new IllegalArgumentException(text);
        }

        try {
            Files.write(Path.of(pathName), text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e +  " - Неудачная попытка записи файла");
        }
    }
}
