package battleship;

public class ArrayMove {



    int [] returnCoordinate = new int[2];
    String [][] arrayName;
    int [] startCoord;
    boolean isInRange;
    int minColumnNum;
    int maxColumnNum;
    int minRowNum;
    int maxRowNum;

    public ArrayMove(String [][] arrayName, int [] startCoord) {
        this.arrayName = arrayName;
        this.startCoord = startCoord;
        returnCoordinate = startCoord;

        minColumnNum = 0;
        maxColumnNum = arrayName.length;
        minRowNum = 0;
        maxRowNum = arrayName[0].length;

    }

    //direction - UP DOWN LEFT RIGHT
//    public int [] move(String direction){
    public boolean move(String direction){
//        isMoveToTheNextDirection(direction); //can we move to the particular direction? set boolean var

        if(isMoveToTheNextDirection(direction)){
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


    boolean isMoveToTheNextDirection(String direction){

        switch (direction){
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
//            default:

        }
        return isInRange;
    }

    private void isLeft() {
//        isInRange=startCoord[1]>0;
        isInRange=returnCoordinate[1]>minRowNum;

    }

    private void isRight() {

//        isInRange=startCoord[1]<10;
        isInRange=returnCoordinate[1]<maxRowNum;
    }

    private void isDown() {

//        isInRange=arrayName.length>arrayName.length-1;
        isInRange=returnCoordinate[0]<maxColumnNum;
    }

    private void isUp() {
        isInRange=returnCoordinate[0]>minColumnNum;
    }


    private void left() {
        if(isInRange){
            returnCoordinate[0] = startCoord[0];
            returnCoordinate[1] = startCoord[1]-1;

        }
    }

    private void right() {
        if(isInRange){
            returnCoordinate[0] = startCoord[0];
            returnCoordinate[1] = startCoord[1]+1;

        }
    }

    private void down() {
        if(isInRange){
            returnCoordinate[0] = startCoord[0]+1;
            returnCoordinate[1] = startCoord[1];

        }
    }

    private void up() {
        if(isInRange){
            returnCoordinate[0] = startCoord[0]-1;
            returnCoordinate[1] = startCoord[1];

        }

    }

    public int[] getReturnCoordinate() {
        return returnCoordinate;
    }


    private boolean checkNeighbor(String rowOrColumn){

        //ha sor alsó felső sor ellenőrzése
        //ha oszlop akkor jobb bal oszlop ellenőrzése

//        String[] coordinates = coordinate.split(" ");
//        int rowSecNum = Integer.parseInt(coordinates[0].substring(1, 2));
//
//        int range = coordinates[0].length() == 2?2:3; //because of F9 F10 +char
//        int rowFirstNum = Integer.parseInt(coordinates[0].substring(1, range));
//
//        row = coordinates[0].substring(0, 1).equals(coordinates[1].substring(0, 1));
//        column = coordinates[0].substring(1, 2).equals(coordinates[1].substring(1, 2));

//        if(row){
//
//        } else if (column) {
//
//        }


        return false;
    }



    @Override
    public String toString() {
        return returnCoordinate[0] + "  " + returnCoordinate[1];
    }
}
