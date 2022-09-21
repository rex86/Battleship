package battleship;

public class Player {

    String name;
    Battleship[] battleships = new Battleship[5];
    Table table = new Table(10,10);

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Battleship[] getBattleships() {
        return battleships;
    }

    public void setBattleships(Battleship[] battleships) {
        this.battleships = battleships;
    }
    public Table getTable() {
        return table;
    }

}
