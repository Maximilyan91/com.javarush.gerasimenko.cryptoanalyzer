package service.impl;

import service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    //TODO добавить проверки, валидацию пути и текста в оба метода

    @Override
    public String readFile(String pathName) {
        String text;
        try {
            text = Files.readString(Path.of(pathName));
        } catch (IOException e) {
            throw new RuntimeException(e + " - exception when trying to read a file");
        }
        return text;
    }

    @Override
    public void writeFile(String pathName, String text) {
        try {
            Files.write(Path.of(pathName), text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e + " - exception when trying to write a file");
        }
    }
}
