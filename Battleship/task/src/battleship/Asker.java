package battleship;

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

    static String userInput(String question){

        Scanner scanner = new Scanner(System.in);
        String input="";
        System.out.println(question);
        return scanner.nextLine();

    }

}
