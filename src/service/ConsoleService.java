package service;

import model.Languages;

public interface ConsoleService {
    void printGreeting();

    void printMainMenu();

    String requestFile();

    int requestKey();

    Languages requestLang();

    void inputEncrypt();
}
