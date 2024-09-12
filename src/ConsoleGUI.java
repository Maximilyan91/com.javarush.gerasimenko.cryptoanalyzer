import service.ConsoleService;
import service.Cryptographer;
import service.impl.ConsoleServiceImpl;
import service.impl.CryptographerImpl;

import java.util.Scanner;

public class ConsoleGUI {

    private static final ConsoleService service = new ConsoleServiceImpl();
    private static final Cryptographer cryptographer = new CryptographerImpl();


    public static void mainMenu() {
        service.printGreeting();
 //       service.printMainMenu();
        Scanner scanner = new Scanner(System.in);
        boolean isOpenApp = true;
        while (isOpenApp) {
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
