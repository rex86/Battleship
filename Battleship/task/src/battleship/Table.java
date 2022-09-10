package battleship;

import java.util.Arrays;

public class Table {


    public String[][] getTable() {
        return table;
    }

    private String[][] table;
    private TableSymbols denote; // '~','M','X'..

    boolean row = false;

//    @Override
//    public String toString() {
//        return table[0][];
//    }

    boolean column = false;

    public Table(int sizeX, int sizeY) {
        this.table = new String[sizeX][sizeY];
        initTable();
//        inputValidation("A1 A5");
//        addBattleShip("A2 A6",denote);


    }

    public void showTable() {
        header(this.table[0].length);
        body();
    }

    private void header(int number) {
        System.out.print("  ");
        for (int i = 1; i <= number; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    private void body() {
        for (int i = 0; i < this.table.length; i++) {
            System.out.print(" " + ((char) (i + 65)));
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(" " + table[i][j]);
            }
            System.out.println();
        }

    }

    private void initTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = TableSymbols.UNTOUCHED.getSymbol();
            }
        }
    }

    protected void addBattleShip(String coordinate, Battleship battleship) {
        // F3 F7 koordináta leképzése
        // sor - egyezik az 1.char
        // oszlop - egyezik a 2.char
        //amelyik nem egyezik az meg növekszik
        // 1.char-t átalakítani tömbös indexé, int rowNumber = (char) 'A'-65;
        // 2.dim tömbbe eltárolni a hajó koordinátáit (Battleship-ben is van ilyen, setCoordinate?)

        String[] coordinates = coordinate.split(" ");
        int rowSecNum = Integer.parseInt(coordinates[0].substring(1, 2));

        int range = coordinates[0].length() == 2?2:3; //because of F9 F10 +char
        int rowFirstNum = Integer.parseInt(coordinates[0].substring(1, range));

        row = coordinates[0].substring(0, 1).equals(coordinates[1].substring(0, 1));
        column = coordinates[0].substring(1, 2).equals(coordinates[1].substring(1, 2));
        int rowNumber = 0;
        int columnNumber = 0;
//        rowNumber = (char) 'A'-65;
        rowNumber = getArrayIndexFromChar(coordinates[0].charAt(0));
        columnNumber = getArrayIndexFromChar(coordinates[0].charAt(0));

        int[][] shipCoordinate = new int[battleship.getSize()][2];
        int index = rowSecNum;
        if (row) {

            for (int i = 0; i < shipCoordinate.length; i++) {
                shipCoordinate[i][1] = index++;
                shipCoordinate[i][0] = rowNumber;

            }

        } else if (column) {
            for (int i = 0; i < shipCoordinate.length; i++) {
                shipCoordinate[i][0] = rowNumber++;
                shipCoordinate[i][1] = rowFirstNum;

            }
        }

//        for (int[] items: shipCoordinate) {
//            for (int item:items) {
//                System.out.print(item + " ");
//            }
//            System.out.println();
//        }

        //save and update table array
        battleship.setCoordinates(shipCoordinate);
        modifyTableCoordinate(battleship);

//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table[0].length; j++) {
//                table[i][j] = denote.UNTOUCHED.getSymbol();
//            }
//        }
    }

    private int getArrayIndexFromChar(char character) {
        return character - 65;
    }


//    private void setCoordinateToTable() {
//        int[][] shipCoordinate = battleship.getCoordinates();
//        for (int i = 0; i < shipCoordinate.length; i++) {
//            for (int j = 0; j < shipCoordinate.length; j++) {
//
////                shipCoordinate[i][j] = table[i][j];
//
//            }
//        }
//    }

    protected void modifyTableCoordinate(Battleship battleship) {
        int[][] shipCoordinate = battleship.getCoordinates();

//        table[][] = shipCoordinate[][]

        for (int i = 0; i < shipCoordinate.length; i++) {
            for (int j = 0; j < shipCoordinate.length; j++) {
//                System.out.println(shipCoordinate[i][0] + " - " + shipCoordinate[i][1]);
                table[shipCoordinate[i][0]][shipCoordinate[i][1]-1] = "O";
            }

        }
    }

    protected boolean inputValidation(String input){

//        System.out.println("B10 B10".matches("([A-J]([1-9)]|10))\s([A-J]([1-9]|10))"));
        String regExp = "([A-J]([1-9)]|10))\s([A-J]([1-9]|10))";
        boolean inputVal = input.matches(regExp);
        if(inputVal){

            String [] coord = input.split(" ");
            String startCoord   = coord[0];
            String endCoord   = coord[1];
            int x1 = getArrayIndexFromChar(startCoord.charAt(0));
            int y1 = Integer.parseInt(String.valueOf(startCoord.charAt(1)));

            int x2 = getArrayIndexFromChar(endCoord.charAt(0));
            int y2 = Integer.parseInt(String.valueOf(endCoord.charAt(1)));

//            System.out.println(x1+" "+y1+" "+x2+" "+y2);

            //tartomány ellenőrzés sor ill oszlop esetén
//            System.out.println("Order: " + getProperOrderInput(input));
        } else if (inputVal && !row && !column) {
            System.out.println("Error! Wrong ship location! Try again:");
        }

        return false;
    }
    protected String getProperOrderInput(String input){

        String [] coord = input.split(" ");
        String startCoord   = coord[0];
        String endCoord   = coord[1];

        String orderedInput="";
        int startCoorNum=Integer.parseInt(startCoord.substring(1,2));
        int endCoorNum=Integer.parseInt(endCoord.substring(1,2));

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        if(row && (endCoorNum<startCoorNum)){
            orderedInput = endCoord +" "+startCoord;
        }else if(column && (endColumnNum<startColumnNum)) {
            orderedInput = endCoord +" "+startCoord;
        }else {
            orderedInput = input;
        }
        return orderedInput;
    }

    protected int lengthOfGivenCoordinate(String input){
        int sum=0;
        String orderedInput=getProperOrderInput(input);

        String [] coord = orderedInput.split(" ");
        String startCoord   = coord[0];
        String endCoord   = coord[1];


        int startCoorNum=Integer.parseInt(startCoord.substring(1,2));
        int endCoorNum=Integer.parseInt(endCoord.substring(1,2));

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        if(row){
            sum = endCoorNum - startCoorNum;
        }
        else if(column){
            sum = endColumnNum - startColumnNum;
        }
        return sum+1;
    }
    public boolean isRow() {
        return row;
    }

    public boolean isColumn() {
        return column;
    }

}
