package battleship;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class GameController {

    private static Table table = new Table(10, 10);
    private static String fullQuestion = "";
    private static String userInput = "";
    private static String validateStr = " ";
    private static Validating validating = new Validating();

    private static int index = 0;


    public static void start() throws IOException {

        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        fillTheGameTable(p1);
        promptEnterKey();
        fillTheGameTable(p2);
        do {
            promptEnterKey();
            showAllTable(p1, p2);
            if(startGame(p1, p2)) break;

            promptEnterKey();
            showAllTable(p2, p1);
            if(startGame(p2, p1)) break;

        } while (!allShipDestroyed(p1) || !allShipDestroyed(p2));

        System.out.println("You sank the last ship. You won. Congratulations!");

    }

    private static boolean allShipDestroyed(Player player) {

        boolean isAircraftDestroyed = player.getBattleships()[0] == null ? true : false;
        boolean isBattleshipDestroyed = player.getBattleships()[1] == null ? true : false;
        boolean isSubmarineDestroyed = player.getBattleships()[2] == null ? true : false;
        boolean isCruiserDestroyed = player.getBattleships()[3] == null ? true : false;
        boolean isDestroyerDestroyed = player.getBattleships()[4] == null ? true : false;

        return isAircraftDestroyed &&
                isBattleshipDestroyed &&
                isSubmarineDestroyed &&
                isCruiserDestroyed &&
                isDestroyerDestroyed;

    }

    private static boolean startGame(Player player1, Player player2) {
        fullQuestion = player1.getName() + ", it's your turn:";
        String responseStr = "";

        userInput = Asker.userInput(fullQuestion);
        validateStr = validating.shotInputCheck(userInput);

        if (validateStr.equals("OK")) {
            String inp = player2.getTable().changeTableMissOrHit(userInput);
            if (inp.equals("MISS")) {
                responseStr = "You missed.";

            } else if (inp.equals("HIT")) {
                responseStr = "You hit a ship!";

                if (player2.getTable().getBattleshipByCoord(player2.getBattleships(), userInput) != null && !inp.equals("FAKEHIT")) {
                    player2.getTable().getBattleshipByCoord(player2.getBattleships(), userInput).setAttacked();
                }

            } else if (inp.equals("FAKEHIT")) {
                responseStr = "You hit a ship!";
            }
            if (player2.getTable().getBattleshipByCoord(player2.getBattleships(), userInput) != null && player2.getTable().getBattleshipByCoord(player2.getBattleships(), userInput).isDestroyed()) {
                fullQuestion = "You sank a ship!";
                player2.getBattleships()[findIndexOfBattleship(player2.getTable().getBattleshipByCoord(player2.getBattleships(), userInput), player2)] = null;

            } else {
                fullQuestion = responseStr + " Try again:";
            }

            System.out.println(responseStr);

        } else {
            System.out.println(validateStr);
        }

        if(fullQuestion.equals("You sank a ship!")) System.out.println(fullQuestion);
        boolean isGameEnd = allShipDestroyed(player1) || allShipDestroyed(player2);

        return isGameEnd;
    }

    private static int findIndexOfBattleship(Battleship battleship, Player player) {
        int index = 0;

        for (int i = 0; i < player.getBattleships().length; i++) {
            if (battleship.equals(player.getBattleships()[i])) index = i;
        }

        return index;
    }

    private static void fillTheGameTable(Player player) {
        System.out.println(player.getName() + ", place your ships on the game field\n");
        String templateQuestion = "Enter the coordinates of the ";
        Battleship battleship=null;

        int i = 0;
        for (Ships ship : Ships.values()) {
            player.getTable().showTable();
            System.out.println();
            do {
                fullQuestion = templateQuestion + (ship.name().equals("AircraftCarrier") ? "Aircraft Carrier" : ship) + " (" + ship.getShipSize() + " cells):";
                userInput = Asker.userInput(fullQuestion); //ha false újrakérdez
                System.out.println();

                if(userInput.isEmpty() || userInput.isBlank()|| userInput.equals("") || userInput.equals(" ")) continue;
                battleship = new Battleship(ship, 'X');
                validating = new Validating(userInput, battleship, player.getTable());
                validateStr = validating.validateAll();
                if (!validateStr.equals("OK")) System.out.println(validateStr + "\n");
                if (validateStr.equals("OK")) player.getBattleships()[i++] = battleship;

            } while (!validateStr.equals("OK"));
            if(battleship != null) player.getTable().addBattleShip(validating.getInput(), battleship);

        }
        player.getTable().showTable();
        System.out.println();

    }

    private static void showAllTable(Player player1, Player player2) {
        player2.getTable().hideTable();
        System.out.println("----------------------");
        player1.getTable().showTable();
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        try {
            System.in.read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
