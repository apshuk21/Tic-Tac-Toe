package models;

public class Move {
    private int row;
    private int col;
    private Player player;
    private Cell gridCellLinkedWithMove;

    public Move(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getGridCellLinkedWithMove() {
        return gridCellLinkedWithMove;
    }

    public void setGridCellLinkedWithMove(Cell gridCellLinkedWithMove) {
        this.gridCellLinkedWithMove = gridCellLinkedWithMove;
    }
}
