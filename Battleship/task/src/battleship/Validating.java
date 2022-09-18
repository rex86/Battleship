package battleship;

public class Validating {

    public String getInput() {
        return input;
    }

    private String input;
    private Battleship battleship;
    private Table table;
    boolean row = false;
    boolean column = false;

    public Validating(String input, Battleship battleship, Table table) {
        this.battleship = battleship;
        this.table = table;
        this.input = input;
        setProperOrderInput();
    }

    public Validating() {

    }

    public String getValidateStr() {
        return validateStr;
    }

    String validateStr = "OK";

    private void sizeCheck() {
        if (lengthOfGivenCoordinate() != battleship.getSize())
            validateStr = "Error! Wrong length of the " + battleship.getName() + "! Try again:";
    }

    private void rowColumnCheck() {
        if (!row && !column) validateStr = "Error! Wrong ship location! Try again:";
    }

    private void arrangeCheck() {
        if (isShipAround())
            validateStr = "Error! You placed it too close to another one. Try again:";

    }

    private void inputCheck() {
        String regExp1 = "([A-J]([1-9)]|10))\s([A-J]([1-9]|10))";

        if (!(input.matches(regExp1))) validateStr = "Error! You entered the wrong coordinates! Try again:";

    }

    protected String shotInputCheck(String shotInput) {
        String regExp1 = "([A-J]([1-9)]|10))";
        if (!(shotInput.matches(regExp1))) {
            validateStr = "Error! You entered the wrong coordinates! Try again:";
        } else {
            validateStr = "OK";
        }
        return validateStr;

    }

    private void setProperOrderInput() {
        String[] coord = input.split(" ");
        String startCoord = coord[0];
        String endCoord = coord[1];

        int rangeFirstPart = coord[0].length() == 2 ? 2 : 3; //because of F9 F10 +char
        int rangeSecondPart = coord[1].length() == 2 ? 2 : 3; //because of F9 F10 +char

        row = coord[0].substring(0, 1).equals(coord[1].substring(0, 1));
        column = coord[0].substring(1, rangeFirstPart).equals(coord[1].substring(1, rangeSecondPart));

        String orderedInput = "";
        int startCoorNum = Integer.parseInt(startCoord.substring(1, rangeFirstPart));
        int endCoorNum = Integer.parseInt(endCoord.substring(1, rangeSecondPart));

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        if (row && (endCoorNum < startCoorNum)) {
            orderedInput = endCoord + " " + startCoord;
        } else if (column && (endColumnNum < startColumnNum)) {
            orderedInput = endCoord + " " + startCoord;
        } else {
            orderedInput = input;
        }
        this.input = orderedInput;
    }

    private int getArrayIndexFromChar(char character) {
        return character - 65;
    }

    public String validateAll() {
        setProperOrderInput();
        inputCheck();
        sizeCheck();
        rowColumnCheck();
        arrangeCheck();
        return validateStr;
    }


    private int lengthOfGivenCoordinate() {
        int sum = 0;

        String[] coord = this.input.split(" ");
        String startCoord = coord[0];
        String endCoord = coord[1];

        int rangeFirstPart = coord[0].length() == 2 ? 2 : 3; //because of F9 F10 +char
        int rangeSecondPart = coord[1].length() == 2 ? 2 : 3; //because of F9 F10 +char

        int startCoorNum = Integer.parseInt(startCoord.substring(1, rangeFirstPart));
        int endCoorNum = Integer.parseInt(endCoord.substring(1, rangeSecondPart));

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        if (row) {
            sum = endCoorNum - startCoorNum;
        } else if (column) {
            sum = endColumnNum - startColumnNum;
        }
        return sum + 1;
    }

    private boolean isShipAround() {

        String[] coord = this.input.split(" ");
        String startCoord = coord[0];
        String endCoord = coord[1];

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        int rangeFirstPart = coord[0].length() == 2 ? 2 : 3; //because of F9 F10 +char
        int rangeSecondPart = coord[1].length() == 2 ? 2 : 3; //because of F9 F10 +char

        int firstNumber = Integer.parseInt(coord[0].substring(1, rangeFirstPart));
        int secondNumber = Integer.parseInt(coord[1].substring(1, rangeSecondPart));

        int[] start = {startColumnNum, firstNumber - 1};
        int[] newcoord = new int[2];
        ArrayMove arrayMove = new ArrayMove(table.getTable(), start);


        boolean up = false;
        boolean down = false;
        boolean right = false;
        boolean left = false;

        if (row) {

            if (arrayMove.move("UP")) {
                up = hasZeroInRow(arrayMove.getReturnCoordinate(), battleship.getSize());

            }
            if (arrayMove.move("LEFT")) {
                left = hasZeroInRow(arrayMove.getReturnCoordinate(), battleship.getSize());
            }

        } else if (column) {
            if (arrayMove.move("UP")) {
                up = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());

            }
            if (arrayMove.move("LEFT")) {
                left = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());

            }
            if (arrayMove.move("DOWN") && arrayMove.move("DOWN")) {
                down = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());

            }

        }
        return up || down || left || right;
    }

    private boolean hasZeroInRow(int[] coord, int battleshipSize) {
        int rowNumber = coord[0] + 1;
        int colNum = coord[1] - 1;
        int start = colNum;
        int end = 9;

        if (start + battleshipSize < 9) {
            end = start + battleshipSize + 1;
        } else if (rowNumber < 9) {
            end = start + battleshipSize;
        }
        if (start < 0) {
            start = 0;
        }

        for (int i = start; i < end; i++) {

            if (table.getTable()[rowNumber][i].equals("O")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasZeroInColumn(int[] coord, int battleshipSize) {
        int rowNumber = coord[0];
        int colNum = coord[1];
        int start = rowNumber;
        int end = 9;

        if (start + battleshipSize < 9) {
            end = start + battleshipSize + 1;
        } else if (rowNumber < 9) {
            end = start + battleshipSize;
        } else if (rowNumber > 9) {
            end = 9;
        }

        for (int i = start; i < end; i++) {
            if (table.getTable()[i][colNum].equals("O")) {
                return true;
            }
        }

        return false;
    }

}
