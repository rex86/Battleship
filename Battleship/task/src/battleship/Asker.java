package battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Asker {
//
//    String question;
//    boolean ok;
//
//    public Asker(String question, boolean ok) {
//        this.question = question;
//        this.ok = ok;
//    }
    protected static Scanner scanner = new Scanner(System.in);
    static String userInput(String question){

//        Scanner scanner = new Scanner(System.in);

        System.out.println(question);

        return scanner.nextLine();
//        return input;
//        String input = "";
//        try(Scanner scanner = new Scanner(new File("/home/rex/Programming/workplace/java/BattleshipInput.txt"))) {
//            while (scanner.hasNextLine()) {
//                String i = scanner.nextLine();
//                System.out.println(i);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        return input;

    }

}
