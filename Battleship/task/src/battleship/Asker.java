package battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Asker {
    protected static Scanner scanner = new Scanner(System.in);

    static String userInput(String question) {

        System.out.println(question);

        return scanner.nextLine();
    }

}
