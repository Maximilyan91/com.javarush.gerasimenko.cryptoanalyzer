package service;

public interface FileService {

    String readFile(String pathName);

    void writeFile(String pathName, String text);
}
