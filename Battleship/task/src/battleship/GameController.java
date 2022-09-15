package battleship;

import java.util.Scanner;

public class GameController {
    private static Battleship [] battleships = new Battleship[5];
    public static void start(){

        String templateQuestion = "Enter the coordinates of the ";
        String fullQuestion = "";
        String userInput = "";
        String validateStr=" ";

        Validating validating = new Validating();
        Battleship battleship;
        Table table = new Table(10,10);

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
        System.out.println("The game starts!\n");
        table.hideTable();

//        do{

            System.out.println();
            fullQuestion = "Take a shot!\n";
//        do{
//            userInput = Asker.userInput(fullQuestion);
////            validateStr = validating.validateAll();
//            validateStr = validating.shotInputCheck(userInput);
//            fullQuestion = validateStr;
//
//        }while (!validateStr.equals("OK"));

//        System.out.println("USER input: " + userInput);
//        if(validateStr.equals("OK")){
//        System.out.println(table.changeTableMissOrHit(userInput));

        String responseStr = "";
        String scannerInput = "";
        while (Asker.scanner.hasNextLine()){
            scannerInput = Asker.scanner.nextLine();


            do{
                userInput = Asker.userInput(fullQuestion);
//            validateStr = validating.validateAll();
                validateStr = validating.shotInputCheck(userInput);




                String inp = table.changeTableMissOrHit(userInput);
//        System.out.println("INP: " + inp);
                if (inp.equals("MISS")) {
//                    table.showTable();
                    System.out.println();
                    table.hideTable();
                    responseStr = "You missed!";
//                System.out.println("You missed!");

                } else if (inp.equals("HIT")) {
//                    table.showTable();
                    table.hideTable();
                    responseStr = "You hit a ship!";
//                System.out.println("You hit a ship!");
//                System.out.println(table.getBattleshipByCoord(battleships,userInput).getName());
                    if (table.getBattleshipByCoord(battleships, userInput) != null) {
                        table.getBattleshipByCoord(battleships, userInput).setAttacked();
//                    System.out.println("Ship attacked "+table.getBattleshipByCoord(battleships,userInput).getName());
                        System.out.println();
                    }

                }
                fullQuestion = responseStr + " Try again:";
                userInput = Asker.userInput(fullQuestion);
                System.out.println();
                table.hideTable();

//            table.showTable();
            }while (!allShipDestroyed() && validateStr.equals("OK"));
        }



        System.out.println("You sank the last ship. You won. Congratulations!");
//        }while (!validateStr.equals("OK"));
        System.out.println();


    }

    private static boolean allShipDestroyed(){

        int numOfBattleship = 1;
        for (Battleship item: battleships) {
            System.out.println(item.getName() + " has been attacked " + item.getAttackedNum());
            if(item.isDestroyed()) {
                ++numOfBattleship;
                Asker.userInput("You sank a ship! Specify a new target:\n");
            }
        }
        System.out.println("numOfBattleship: "+numOfBattleship);
        return numOfBattleship == 5;
    }

}
