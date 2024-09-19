package util;

public class ConsoleUtils {

    public static void printGreeting() {
        System.out.println("\033[33mAve coder!\033[0m");
    }

    public static void printMainMenu() {
        System.out.println("""
                Введи цифру желаемого действия:
                1 - Зашифровать текст.
                2 - Дешифровать текст.
                3 - Дешифровать текст перебором всех значений
                0 - Выход.""");
    }
}



