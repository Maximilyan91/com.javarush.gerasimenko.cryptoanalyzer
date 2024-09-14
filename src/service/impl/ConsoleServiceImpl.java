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
    public static final Languages DEFAULT_ENCRYPT_LANGUAGE = Languages.RUS;
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
    public void inputEncrypt() {
        String text = requestFile();
        int key = requestKey();
        Languages languages = requestLang();

        String encryptedText = cryptographer.encrypt(languages, text, key);
        System.out.println("Зашифрованный текст:\n" + encryptedText);

        if (validator.isValidEncrypt(text, encryptedText)) {
            saveEncryptedText(encryptedText);
        } else {
            System.out.println("Шифровка выполнена некорректно. Попробуйте снова");
        }
    }

    private String requestFile() {
        boolean isExistFile = false;
        Path path = null;
        while (!isExistFile) {

            System.out.println("Укажите путь к текстовому файлу для шифровки:");

            String s = scanner.nextLine();

            if (s.isEmpty()) {
                break;
            }

            path = Path.of(s);
            isExistFile = validator.isValidFIle(path);

            if (!isExistFile) {
                System.out.println("Файла по пути:\n" + path +
                        " не существует.");
            }

        }
        return fileService.getStringFromFile(path);
    }

    private int requestKey() {
        System.out.println("Укажите сдвиг:");

        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("sdsadas");
            }
        }
    }

    private Languages requestLang() {
        System.out.println("""
                Укажите язык текста:
                1 - RUS
                2 - ENG""");


        while (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            if (choice == 1) {
                break;
            }

            if (choice == 2) {
                return Languages.ENG;
            }
        }
        return DEFAULT_ENCRYPT_LANGUAGE;
    }

    private void saveEncryptedText(String text) {
        System.out.println("Укажите путь для сохранения файла" +
                " с результатом шифровки: ");

        Scanner sc = new Scanner(System.in);
        Path path = Path.of(sc.nextLine());

        try {
            fileService.writeFile(path, text);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}


