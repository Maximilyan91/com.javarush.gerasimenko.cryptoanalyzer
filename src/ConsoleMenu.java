import model.Languages;
import service.ConsoleService;
import service.Cryptographer;
import service.impl.ConsoleServiceImpl;
import service.impl.CryptographerImpl;
import util.ConsoleUtils;

import java.util.Scanner;

public class ConsoleMenu {

    private static final Cryptographer cryptographer = new CryptographerImpl();
    private static final ConsoleService consoleService = new ConsoleServiceImpl();


    public static void mainMenu() {

        ConsoleUtils.printGreeting();
        ConsoleUtils.printMainMenu();
        Scanner scanner = new Scanner(System.in);
        boolean isOpenApp = true;

        while (isOpenApp) {
            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1 -> {
                        Languages lang = consoleService.requestLang();
                        String text = consoleService.requestFile();
                        int key = consoleService.requestKey();
                        cryptographer.encrypt(lang, text, key);
                    }
                    case 0 -> isOpenApp = false;
                }
            } else {
                scanner.next();
                ConsoleUtils.printMainMenu();
            }
        }
    }
}
