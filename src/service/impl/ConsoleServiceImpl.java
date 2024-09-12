package service.impl;

import model.Languages;
import service.ConsoleService;
import service.Cryptographer;
import service.FileService;
import service.Validator;

import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    public static final Cryptographer cryptographer = new CryptographerImpl();
    private static final FileService fileService = new FileServiceImpl();
    private static final Validator validator = new ValidatorImpl();
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void printGreeting() {
        System.out.println("\033[33mAve coder!\033[0m");
    }

    @Override
    public void printMainMenu() {
        System.out.println("""
                Введи цифру желаемого действия:
                1 - Зашифровать текст.
                2 - Дешифровать текст.
                3 - Дешифровать текст перебором всех значений
                0 - Выход.""");
    }

    @Override
    public String requestFile() {
        boolean isExistFile = false;
        Path path = null;
        while (!isExistFile) {
            System.out.println("Укажите путь к текстовому файлу для шифровки:");

            path = Path.of(scanner.nextLine());

            isExistFile = validator.isValidPath(path);

            if (!isExistFile) {
                System.out.println("Файла по пути:\n" + path +
                        " не существует.");
            }

        }
        return fileService.getStringFromFile(path);
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
@Override
    public void inputEncrypt() {
        String text = requestFile();
        int key = requestKey();
        Languages languages = requestLang();
    System.out.println(cryptographer.encrypt(languages, text, key));
}
}
