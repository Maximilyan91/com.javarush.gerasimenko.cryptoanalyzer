package service.impl;

import model.Languages;
import service.ConsoleService;
import service.Cryptographer;
import service.FileService;
import service.Validator;

import java.nio.file.Path;
import java.util.List;
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
    public void enterEncrypt() {
        String text = getFileContent();
        int key = getKey();
        Languages languages = getLang();

        String encryptedText = cryptographer.encrypt(languages, text, key);
        System.out.println("Зашифрованный текст:\n" + encryptedText);

        if (validator.isValidEncrypt(text, encryptedText)) {
            saveText(encryptedText);
        } else {
            System.out.println("Шифровка выполнена некорректно. Попробуйте снова");
        }
    }

    @Override
    public void enterDecrypt() {
        String text = getFileContent();
        int key = getKey();
        Languages languages = getLang();

        String decrypted = cryptographer.decrypt(languages, text, key);

        System.out.println("Дешифрованный текст:\n" + decrypted);

        if (validator.isValidEncrypt(text, decrypted)) {
            saveText(decrypted);
        } else {
            System.out.println("Дешифровка выполнена некорректно. Попробуйте снова");
        }
    }

    @Override
    public void enterBruteForce() {
        Languages lang = getLang();
        String encrypted = getFileContent();
        String encSubString = getSubString(encrypted);

        List<String> results = cryptographer.bruteForce(lang, encSubString);
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + " - " + results.get(i) + "\n ------------------------------");
        }

        System.out.println("Выберите правильный вариант дешифровки и введите его номер:");
        int key = scanner.nextInt();

        String decrypted = cryptographer.decrypt(lang, encrypted, key);

        System.out.println("Дешифрованный текст:\n" + decrypted);

        if (validator.isValidEncrypt(encrypted, decrypted)) {
            saveText(decrypted);
        } else {
            System.out.println("Дешифровка выполнена некорректно. Попробуйте снова");
        }
    }

    private String getFileContent() {
        boolean isExistFile = false;
        Path path = null;
        while (!isExistFile) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Укажите путь к исходному текстовому файлу:");
            String s = sc.nextLine();

            path = Path.of(s);
            isExistFile = validator.isValidFIle(path);

            if (!isExistFile) {
                System.out.println("Файла по пути:\n" + path +
                        " не существует.");
            }
        }
        return fileService.getStringFromFile(path);
    }

    private int getKey() {
        System.out.println("Укажите сдвиг:");

        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Введите число не равное 0 и не кратное количеству символов в языке");
            }
        }
    }

    private Languages getLang() {
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

    private void saveText(String text) {
        System.out.println("Укажите путь для сохранения файла" +
                " с результатом: ");

        Scanner sc = new Scanner(System.in);
        Path path = Path.of(sc.nextLine());

        try {
            fileService.writeFile(path, text);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getSubString(String text) {
        System.out.println("Укажите количество первых символов текста для перебора:");
        int count = scanner.nextInt();

        if (count < text.length()) {
            return String.copyValueOf(text.toCharArray(), 0, count);
        } else {
            return text;
        }
    }
}


