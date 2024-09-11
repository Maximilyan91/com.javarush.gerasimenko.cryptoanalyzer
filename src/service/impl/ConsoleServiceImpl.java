package service.impl;

import exception.IllegalLanguageException;
import model.Languages;
import service.ConsoleService;
import service.FileService;

import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    private static final FileService fileService = new FileServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String requestFile() {
        System.out.println("Укажите путь к текстовому файлу для шифровки:");

        return fileService.readFile(scanner.nextLine());
    }

    @Override
    public int requestKey() {
        System.out.println("Укажите ключ:");
        int key = scanner.nextInt();
        return key;

    }

    @Override
    public Languages requestLang() {
        System.out.println("""
                Укажите язык текста:
                1 - RUS.
                2 - ENG""");

        while (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            if (choice == 1) {
                return Languages.RUS;
            } else if (choice == 2) {
                return Languages.ENG;
            } else {
                System.out.println("введите 1 или 2");
            }
        }
        return null;
    }
}
