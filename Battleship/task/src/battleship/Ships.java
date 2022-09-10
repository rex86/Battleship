package battleship;

public enum Ships {
    AircraftCarrier(5),
    Battleship(4),
    Submarine(3),
    Cruiser(3),
    Destroyer(2);


    private final int shipSize;

    Ships(int shipSize) {
        this.shipSize = shipSize;
    }

    public int getShipSize() {
        return shipSize;
    }
}
