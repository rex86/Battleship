package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {




//        table.addBattleShip("A9 C9",bs2);
//        table.inputValidation("E10 A10");
//        System.out.println(table.lengthOfGivenCoordinate("A2 A5"));
//        table.showTable();
//        String input = "";
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
//        input = scanner.nextLine();
//        battleships[0] = new Battleship(Ships.AircraftCarrier,'O');
//        table.addBattleShip(table.getProperOrderInput(input),battleships[0]);

        GameController.start();


        /* TODO
            TAKECAREOFINDEX 10...
            2 hajó között legalább 1 helynek ki kell maradnia
            the ships should not cross or touch each other.
        *    */

    }
}
