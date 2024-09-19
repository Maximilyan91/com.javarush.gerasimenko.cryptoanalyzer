package service;

import java.nio.file.Path;

public interface FileService {

    String getStringFromFile(Path path);

    void writeFile(Path path, String text);
}
