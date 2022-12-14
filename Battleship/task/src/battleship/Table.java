package battleship;

import java.util.Arrays;

public class Table {


    public String[][] getTable() {
        return table;
    }

    private String[][] table;
    private TableSymbols denote; // '~','M','X'..
    boolean row = false;
    boolean column = false;

    public Table(int sizeX, int sizeY) {
        this.table = new String[sizeX][sizeY];
        initTable();
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

        int range = coordinates[0].length() == 2 ? 2 : 3; //because of F9 F10 +char
        int rowFirstNum = Integer.parseInt(coordinates[0].substring(1, range));

        row = coordinates[0].substring(0, 1).equals(coordinates[1].substring(0, 1));
        column = coordinates[0].substring(1, 2).equals(coordinates[1].substring(1, 2));
        int rowNumber = 0;
        int columnNumber = 0;
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

        //save and update table array
        battleship.setCoordinates(shipCoordinate);
        modifyTableCoordinate(battleship);

    }

    private int getArrayIndexFromChar(char character) {
        return character - 65;
    }

    protected void modifyTableCoordinate(Battleship battleship) {
        int[][] shipCoordinate = battleship.getCoordinates();

        for (int i = 0; i < shipCoordinate.length; i++) {
            for (int j = 0; j < shipCoordinate.length; j++) {
                table[shipCoordinate[i][0]][shipCoordinate[i][1] - 1] = "O";
            }
        }
    }

    protected String getProperOrderInput(String input) {

        String[] coord = input.split(" ");
        String startCoord = coord[0];
        String endCoord = coord[1];

        String orderedInput = "";
        int startCoorNum = Integer.parseInt(startCoord.substring(1, 2));
        int endCoorNum = Integer.parseInt(endCoord.substring(1, 2));

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        if (row && (endCoorNum < startCoorNum)) {
            orderedInput = endCoord + " " + startCoord;
        } else if (column && (endColumnNum < startColumnNum)) {
            orderedInput = endCoord + " " + startCoord;
        } else {
            orderedInput = input;
        }
        return orderedInput;
    }

    public String changeTableMissOrHit(String input) {


        int range = input.length() == 2 ? 2 : 3; //because of F9 F10 +char
        int rowNumber = getArrayIndexFromChar(input.charAt(0));
        int columnNum = Integer.parseInt(input.substring(1, range)) - 1;

        //because of task description, previous hit or miss
        boolean hit = table[rowNumber][columnNum].equals("O");
        boolean miss = table[rowNumber][columnNum].equals("~") || table[rowNumber][columnNum].equals("M");
        boolean fakeHit = table[rowNumber][columnNum].equals("X");

        String result = "";
        if (hit) {
            table[rowNumber][columnNum] = TableSymbols.HIT.getSymbol();
            result = "HIT";
        } else if (miss) {
            table[rowNumber][columnNum] = TableSymbols.MISS.getSymbol();
            result = "MISS";
        } else if (fakeHit) {
            table[rowNumber][columnNum] = TableSymbols.HIT.getSymbol();
            result = "FAKEHIT";
        }
        return result;

    }

    protected void hideTable() {
        header(this.table[0].length);
        for (int i = 0; i < this.table.length; i++) {
            System.out.print(" " + ((char) (i + 65)));
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].equals("O")) {
                    System.out.print(" " + TableSymbols.UNTOUCHED.getSymbol());

                } else {
                    System.out.print(" " + table[i][j]);
                }
            }
            System.out.println();
        }
    }

    protected Battleship getBattleshipByCoord(Battleship[] battleships, String coord) {

        int range = coord.length() == 2 ? 2 : 3; //because of F9 F10 +char
        int rowNumber = getArrayIndexFromChar(coord.charAt(0));
        int columnNum = Integer.parseInt(coord.substring(1, range));
        for (Battleship battleship : battleships) {
            if (battleship == null) continue;
            for (int[] item : battleship.getCoordinates()) {
                if (item[0] == rowNumber && item[1] == columnNum) {
                    return battleship;
                }
            }
        }
        return null;
    }
}
