package battleship;

public class ArrayMove {

    int[] returnCoordinate = new int[2];
    String[][] arrayName;
    int[] startCoord;
    boolean isInRange;
    int minColumnNum;
    int maxColumnNum;
    int minRowNum;
    int maxRowNum;

    public ArrayMove(String[][] arrayName, int[] startCoord) {
        this.arrayName = arrayName;
        this.startCoord = startCoord;
        returnCoordinate = startCoord;

        minColumnNum = 0;
        maxColumnNum = arrayName.length;
        minRowNum = 0;
        maxRowNum = arrayName[0].length;

    }

    public boolean move(String direction) {

        if (isMoveToTheNextDirection(direction)) {
            switch (direction) {
                case "UP":
                    up();
                    break;
                case "DOWN":
                    down();
                    break;
                case "RIGHT":
                    right();
                    break;
                case "LEFT":
                    left();
                    break;
            }
        }

        return isMoveToTheNextDirection(direction);
    }

    boolean isMoveToTheNextDirection(String direction) {

        switch (direction) {
            case "UP":
                isUp();
                break;
            case "DOWN":
                isDown();
                break;
            case "RIGHT":
                isRight();
                break;
            case "LEFT":
                isLeft();
                break;

        }
        return isInRange;
    }

    private void isLeft() {
        isInRange = returnCoordinate[1] > minRowNum;

    }

    private void isRight() {

        isInRange = returnCoordinate[1] < maxRowNum;
    }

    private void isDown() {

        isInRange = returnCoordinate[0] < maxColumnNum;
    }

    private void isUp() {
        isInRange = returnCoordinate[0] > minColumnNum;
    }


    private void left() {
        if (isInRange) {
            returnCoordinate[0] = startCoord[0];
            returnCoordinate[1] = startCoord[1] - 1;

        }
    }

    private void right() {
        if (isInRange) {
            returnCoordinate[0] = startCoord[0];
            returnCoordinate[1] = startCoord[1] + 1;

        }
    }

    private void down() {
        if (isInRange) {
            returnCoordinate[0] = startCoord[0] + 1;
            returnCoordinate[1] = startCoord[1];

        }
    }

    private void up() {
        if (isInRange) {
            returnCoordinate[0] = startCoord[0] - 1;
            returnCoordinate[1] = startCoord[1];

        }

    }

    public int[] getReturnCoordinate() {
        return returnCoordinate;
    }

    @Override
    public String toString() {
        return returnCoordinate[0] + "  " + returnCoordinate[1];
    }
}
