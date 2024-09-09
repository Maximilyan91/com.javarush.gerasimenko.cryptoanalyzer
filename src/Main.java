import model.Languages;
import service.Cryptographer;
import service.impl.CryptographerImpl;

public class Main {
    public static void main(String[] args) {
        Cryptographer cr = new CryptographerImpl();

        System.out.println(cr.encrypt(Languages.ENG, "abcdef", 94));
        System.out.println(cr.decrypt(Languages.ENG, "cdefgh", 2));


        System.out.println("'а','б','в','г','д','е','ж','з','и','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','я'".toUpperCase());
    }
}