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

    private byte attackedNum;

    public Battleship(Ships ship, char denote) {
        this.size = ship.getShipSize();
        this.denote = denote;
        this.name = ship.name();
        this.attackedNum = 0;
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
        this.attackedNum++;
        isDestroyed = (this.attackedNum == this.size) ? true : false;
    }

}
