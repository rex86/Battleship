package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class GameController {
    private static Battleship [] battleships = new Battleship[5];
    private static Table table = new Table(10,10);
    private static String fullQuestion = "";
    private static String userInput = "";
    private static String validateStr=" ";
    private static Validating validating = new Validating();

    private static int index = 0;


    public static void start(){


        String templateQuestion = "Enter the coordinates of the ";


        Battleship battleship;

        int i = 0;
        for (Ships ship:Ships.values()) {
            table.showTable();
            System.out.println();
            do{
                fullQuestion = templateQuestion + (ship.name().equals("AircraftCarrier")?"Aircraft Carrier":ship) +" ("+ship.getShipSize()+" cells):";
                userInput = Asker.userInput(fullQuestion); //ha false újrakérdez
//                System.out.println("UserINPUT: " + userInput);

                System.out.println();
                battleship = new Battleship(ship,'X');
                validating = new Validating(userInput, battleship,table);
                validateStr = validating.validateAll();
                if(!validateStr.equals("OK")) System.out.println(validateStr+"\n");
                if(validateStr.equals("OK")) battleships[i++] = battleship;

            }while (!validateStr.equals("OK"));

            table.addBattleShip(validating.getInput(),battleship);

        }
        table.showTable();
        System.out.println();
        startGame();

    }

    private static boolean allShipDestroyed(){

        boolean isAircraftDestroyed = battleships[0]==null?true:false;
        boolean isBattleshipDestroyed = battleships[1]==null?true:false;
        boolean isSubmarineDestroyed = battleships[2]==null?true:false;
        boolean isCruiserDestroyed = battleships[3]==null?true:false;
        boolean isDestroyerDestroyed = battleships[4]==null?true:false;;


//        int numOfBattleship = 1;
//        for (Battleship item: battleships) {
////            System.out.println(item.getName() + " has been attacked " + item.getAttackedNum());
//            if(item.isDestroyed()) {
//                ++numOfBattleship;
//                Asker.userInput("You sank a ship! Specify a new target:\n");
//            }
//        }
        return isAircraftDestroyed &&
                isBattleshipDestroyed &&
                isSubmarineDestroyed &&
                isCruiserDestroyed &&
                isDestroyerDestroyed;

//        return numOfBattleship == 5;
    }

    private static void startGame(){

        fullQuestion = "Take a shot!\n";
        System.out.println("The game starts!\n");
        table.hideTable();
        String responseStr = "";
        do{
            userInput = Asker.userInput(fullQuestion);
            validateStr = validating.shotInputCheck(userInput);

            if(validateStr.equals("OK")){
//                System.out.println("Index: " + index++);
                System.out.println();
                String inp = table.changeTableMissOrHit(userInput);
//                System.out.println("INP: " + inp);
                if (inp.equals("MISS")) {
                    System.out.println();
                    table.hideTable();
                    responseStr = "You missed.";

                } else if (inp.equals("HIT")) {
//                    table.showTable();
                    table.hideTable();
                    responseStr = "You hit a ship!";
//                System.out.println("You hit a ship!");
//                System.out.println(table.getBattleshipByCoord(battleships,userInput).getName());

                    if (table.getBattleshipByCoord(battleships, userInput) != null && !inp.equals("FAKEHIT")) {
                        table.getBattleshipByCoord(battleships, userInput).setAttacked();

//                    System.out.println("Ship attacked "+table.getBattleshipByCoord(battleships,userInput).getName());
                        System.out.println();
                    }

                }else if (inp.equals("FAKEHIT")){
                    table.hideTable();
                    responseStr = "You hit a ship!";
                }
//                table.hideTable();
                if(table.getBattleshipByCoord(battleships, userInput) != null && table.getBattleshipByCoord(battleships, userInput).isDestroyed()){
                    fullQuestion = "You sank a ship! Specify a new target";
                    battleships[findIndexOfBattleship(table.getBattleshipByCoord(battleships, userInput))] = null;

                }else {
                    fullQuestion = responseStr + " Try again:";
                }

//                userInput = Asker.userInput(fullQuestion);
                System.out.println();
//                table.hideTable();

            }else{
                System.out.println(validateStr);
            }

//            table.showTable();
        }while (!allShipDestroyed());

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static int findIndexOfBattleship(Battleship battleship){
        int index = 0;

            for (int i=0;i<battleships.length;i++) {
                if(battleship.equals(battleships[i])) index = i;


        }

        return index;
    }

}
