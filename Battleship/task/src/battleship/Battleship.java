package battleship;

public class Battleship {

    public String getName() {
        return name;
    }

    private String name;
    private int size; //cells number
    private int coordinates[][];
    private char denote; // 'O' char
    private boolean isDestroyed;

    private byte attackedNum; //melyik részét érte a támadás

    public Battleship(Ships ship, char denote) {
//
//        switch (ship){
//            case AircraftCarrier:
//                this.size = 5;
//                break;
//            case Battleship:
//                this.size = 4;
//                break;
//            case Submarine:
//
//            case Cruiser:
//                this.size = 3;
//                break;
//
//            case Destroyer:
//                this.size = 2;
//                break;
//        }
        this.size = ship.getShipSize();
        this.denote = denote;
        this.name = ship.name();
    }

    public int getSize() {
        return size;
    }



    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public char getDenote() {
        return denote;
    }

    public void setDenote(char denote) {
        this.denote = denote;
    }

    public boolean isDestroyed() {

        return isDestroyed;
    }


    public byte getAttackedNum() {
        return attackedNum;
    }

    public void setAttacked() {
        this.attackedNum += attackedNum;
        isDestroyed = (this.attackedNum == this.size)?true:false;
    }

}
