import service.ConsoleService;
import service.impl.ConsoleServiceImpl;

import java.util.Scanner;

public class ConsoleGUI {

    private static final ConsoleService service = new ConsoleServiceImpl();

    public static void mainMenu() {
        service.printGreeting();
        boolean isOpenApp = true;

        while (isOpenApp) {
            Scanner scanner = new Scanner(System.in);
            service.printMainMenu();
            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1 -> service.inputEncrypt();
                    //TODO записать шифр в файл
                    case 2 -> {
                        //TODO заняться дешифровкой
                    }
                    case 0 -> isOpenApp = false;
                }
            } else {
                scanner.next();
                service.printMainMenu();
            }
        }
    }
}
