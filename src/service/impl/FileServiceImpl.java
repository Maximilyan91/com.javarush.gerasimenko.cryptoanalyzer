package service.impl;

import service.FileService;
import service.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    private static final String DEFAULT_DIRECTORY = "resources";
    private static final String DEFAULT_FILENAME = "encrypted.txt";
    private static final Validator VALIDATOR = new ValidatorImpl();

    @Override
    public String getStringFromFile(Path path) {
        String text;
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e + " - Неудачная попытка чтения файла");
        }

        if (!VALIDATOR.isValidString(text)) {
            throw new IllegalArgumentException(text);
        } else {
            return text;
        }
    }

    @Override
    public void writeFile(Path path, String text) {
        try {
            if (Files.isRegularFile(path)) {
                Files.writeString(path, text);

            }else if (Files.isDirectory(path)) {

                Path file = Files.createFile(path.resolve(DEFAULT_FILENAME));
                Files.writeString(file, text);

            } else {
                Path file = Path.of(DEFAULT_DIRECTORY, DEFAULT_FILENAME);
                Files.writeString(file, text);
                System.out.println("Путь к файлу указан неправильно.\n" +
                        "Результат сохранен в дефолтную папку");
            }
        } catch (IOException e) {
            throw new RuntimeException("Неудачная попытка записи файла");
        }
    }
}
