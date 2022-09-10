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
//        int[] startCoord = new int[]{9, 0};
//        ArrayMove arrayMove = new ArrayMove(table.getTable(),startCoord);
//        System.out.println(arrayMove.move("DOWN"));
////        arrayMove.move("RIGHT");
//
//
//        System.out.println(arrayMove);

        for (Ships ship:Ships.values()) {
            table.showTable();
            do{
                fullQuestion = templateQuestion + ship +" ("+ship.getShipSize()+" cells):";
                userInput = Asker.userInput(fullQuestion); //ha false újrakérdez
                battleship = new Battleship(ship,'X');
                validating = new Validating(userInput, battleship,table);
                validateStr = validating.validateAll();
                if(!validateStr.equals("OK")) System.out.println(validateStr+"\n");
            }while (!validateStr.equals("OK"));

            table.addBattleShip(validating.getInput(),battleship);
//            table.showTable();
        }


    }


}
