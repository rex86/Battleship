package battleship;

public class GameController {

    public static void start(){
        String templateQuestion = "Enter the coordinates of the ";
        String fullQuestion = "";
        String userInput = "";
        String validateStr=" ";

        Validating validating = new Validating();
        Battleship [] battleships = new Battleship[5];
        Battleship battleship;
        Table table = new Table(10,10);

        int i = 0;
        for (Ships ship:Ships.values()) {
            table.showTable();
            System.out.println();
            do{
                fullQuestion = templateQuestion + (ship.name().equals("AircraftCarrier")?"Aircraft Carrier":ship) +" ("+ship.getShipSize()+" cells):";
                userInput = Asker.userInput(fullQuestion); //ha false újrakérdez
                System.out.println();
                battleship = new Battleship(ship,'X');
                validating = new Validating(userInput, battleship,table);
                validateStr = validating.validateAll();
                if(!validateStr.equals("OK")) System.out.println(validateStr+"\n");
                battleships[i++] = battleship;
            }while (!validateStr.equals("OK"));

            table.addBattleShip(validating.getInput(),battleship);

        }

        table.showTable();
        System.out.println("The game starts!\n");
        table.showTable();
        System.out.println(battleships);
    }


}
