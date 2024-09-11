package exception;

public class IllegalLanguageException extends RuntimeException{

    public IllegalLanguageException() {
        super("Язык текста указан неверно");
    }
}
