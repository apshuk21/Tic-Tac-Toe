package models;

public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Symbol symbol;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
        this.symbol = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void display() {
        if (symbol != null) {
            System.out.print("| " + symbol.getSymbol() + " |");
        } else {
            System.out.print("| - |");
        }
    }
}
