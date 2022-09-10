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
        if (lengthOfGivenCoordinate() != battleship.getSize()) validateStr = "Error! Wrong length of the " + battleship.getName()+"! Try again:";
    }

    private void rowColumnCheck() {
        if (!row && !column) validateStr = "Error! Wrong ship location! Try again:";
    }

    private void arrangeCheck() {
        if(isShipAround())
        validateStr = "Error! You placed it too close to another one. Try again:";

    }

    private void setProperOrderInput() {
        String[] coord = input.split(" ");
        String startCoord = coord[0];
        String endCoord = coord[1];

        int rangeFirstPart = coord[0].length() == 2?2:3; //because of F9 F10 +char
        int rangeSecondPart = coord[1].length() == 2?2:3; //because of F9 F10 +char

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

        int rangeFirstPart = coord[0].length() == 2?2:3; //because of F9 F10 +char
        int rangeSecondPart = coord[1].length() == 2?2:3; //because of F9 F10 +char

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

    private boolean isShipAround(){

        String[] coord = this.input.split(" ");
        String startCoord = coord[0];
        String endCoord = coord[1];

        int startColumnNum = getArrayIndexFromChar(startCoord.charAt(0));
        int endColumnNum = getArrayIndexFromChar(endCoord.charAt(0));

        int rangeFirstPart = coord[0].length() == 2?2:3; //because of F9 F10 +char
        int rangeSecondPart = coord[1].length() == 2?2:3; //because of F9 F10 +char

        int firstNumber = Integer.parseInt(coord[0].substring(1,rangeFirstPart));
        int secondNumber = Integer.parseInt(coord[1].substring(1,rangeSecondPart));

        int [] start = {startColumnNum,firstNumber-1};
        int [] newcoord = new int[2];
        ArrayMove arrayMove = new ArrayMove(table.getTable(), start);


        boolean up=false;
        boolean down=false;
        boolean right = false;
        boolean left = false;
        boolean actualColumn = false;
//        System.out.println(firstNumber + " - " + secondNumber);
        //upper
//        if(row && (firstNumber<table.getTable()[0].length && secondNumber<table.getTable()[0].length)){
////        if(row){
//// -1 elcsúsztatás és kell még -1 hogy az előtte való mezőt is nézzem ezért -2
//            for(int i = firstNumber-2;i<secondNumber+1;i++){
//
//                down = table.getTable()[startColumnNum+1][i].equals("O");
//                up = table.getTable()[startColumnNum-1][i].equals("O");
//
//            }
//        } else if (column && (firstNumber<table.getTable().length && secondNumber<table.getTable().length)) {
////        } else if (column) {
//            for(int i = startColumnNum-1;i<endColumnNum+1;i++){
//                left = table.getTable()[i][startColumnNum-1].equals("O");
//                right = table.getTable()[i][startColumnNum+1].equals("O");
//            }
//
//        }
////        System.out.println(up+" - "+down+" - "+right+" - "+left);

        if(row){
//            if(arrayMove.move("LEFT")){
//                System.out.println("returnCoord " + arrayMove);
//                for(int i = arrayMove.returnCoordinate[0]-1;i<arrayMove.returnCoordinate[0]+2;i++){
//                    System.out.println("TABLE: " + arrayMove + "i - " + i);
//                    if(table.getTable()[i][arrayMove.returnCoordinate[0]].equals("O")){
//                        left = true;
//                        break;
//                    }
//                }
//            }

//
            if(arrayMove.move("UP")){
                up = hasZeroInRow(arrayMove.getReturnCoordinate(), battleship.getSize());
                System.out.println("UP: " + arrayMove);

            }
            if(arrayMove.move("LEFT")){
                left = hasZeroInRow(arrayMove.getReturnCoordinate(), battleship.getSize());
                System.out.println("LEFT: " + arrayMove);
            }

//            if(arrayMove.move("DOWN")){
//                down = hasZeroInRow(arrayMove.getReturnCoordinate(), battleship.getSize());
//
//            }
        } else if (column) {
//            System.out.println("Column coord: " + arrayMove);
            if(arrayMove.move("UP")){
                up = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());
                System.out.println("C_UP: " + arrayMove);

            }
            if(arrayMove.move("LEFT")){
                left = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());
                System.out.println("C_LEFT: " + arrayMove);


            }
            if(arrayMove.move("DOWN") && arrayMove.move("DOWN")){
                down = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());
                System.out.println("C_DOWNDOWN: " + arrayMove);

            }
//            arrayMove.move("LEFT");
//                actualColumn = hasZeroInColumn(arrayMove.getReturnCoordinate(), battleship.getSize());


        }
        System.out.println("Ship around: " + up +" " + right + " " + left + " "+down);

        return up || down || left ||  right;


    }

    private boolean hasZeroInRow(int[] coord, int battleshipSize){
        int rowNumber = coord[0]+1;
        int colNum = coord[1]-1;
        int start = colNum;
        int end;

        if(start+battleshipSize<9){
            end = start+battleshipSize+1;
        }else{
            end = start+battleshipSize;
        }

        System.out.println("hasZeroInRow: " + rowNumber + " - " + colNum);
        for (int i=start;i<end;i++){
//            System.out.println("TABLE - " + table.getTable()[rowNumber][i]);

            if(table.getTable()[rowNumber][i].equals("O")){
                return true;
            }
        }

        return false;
    }

    private boolean hasZeroInColumn(int[] coord, int battleshipSize){
        int rowNumber = coord[0];
        int colNum = coord[1];
        int start = rowNumber;
        int end;

        if(start+battleshipSize<9){
            end = start+battleshipSize+1;
        }else{
            end = start+battleshipSize;
        }

        System.out.println("hasZeroInColumn: \n"+
                "rowNumber: " + rowNumber+
                " colNum: " + colNum+
                " start: " + start+
                " end: " + end);

//        for (int i=colNumber;i<colNumber+battleshipSize-1;i++){
//            System.out.println("C_TABLE - " + table.getTable()[i][rowNumber]);
//            System.out.println("############## "+i+" #############");
//            if(table.getTable()[i][rowNumber].equals("O")){
////            if(table.getTable()[1][2].equals("O")){
//                System.out.println("PAFF");
//                return true;
//            }
//        }
//        System.out.println("END - "+end);
        for (int i=start;i<end;i++){
            System.out.println("########## " + i + " ############");
            System.out.println("TABLE - " + i +" ----> " +colNum + table.getTable()[i][colNum]);
            if(table.getTable()[i][colNum].equals("O")){
                return true;
            }
        }

        return false;

    }

}
