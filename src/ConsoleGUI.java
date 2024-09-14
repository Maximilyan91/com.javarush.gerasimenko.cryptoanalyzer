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
                    case 1 -> service.enterEncrypt();
                    case 2 -> service.enterDecrypt();
                    case 3 -> service.enterBruteForce();
                    case 0 -> isOpenApp = false;
                }

            } else {
                scanner.next();
                service.printMainMenu();
            }
        }
    }
}
