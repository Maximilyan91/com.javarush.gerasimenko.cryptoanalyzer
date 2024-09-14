package service.impl;

import service.FileService;
import service.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

   private static final Validator validator = new ValidatorImpl();
    public static final String DEFAULT_DIRECTORY = "resources";
    public static final String DEFAULT_FILENAME = "encrypted.txt";

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
    public void writeFile(Path path, String text) {
        try {
            if (Files.exists(path)) {
                Files.writeString(path, text);
            }

            if (Files.isDirectory(path)) {
                Path file = Files.createFile(path.resolve(DEFAULT_FILENAME));
                Files.writeString(file, text);
            }
            if (Files.notExists(path)) {
                Path file = Files.createFile(Path.of(DEFAULT_DIRECTORY, path.toString()));
                Files.writeString(file, text);
            } else {
                Files.writeString(Path.of(DEFAULT_DIRECTORY, DEFAULT_FILENAME), text);
            }

        } catch (IOException e) {
            throw new RuntimeException("Неудачная попытка записи файла");
        }
    }
}
