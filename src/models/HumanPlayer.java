package models;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(int id, String name, PlayerType playerType, Symbol symbol) {
        super(id, name, playerType, symbol);
    }

    @Override
    public Move makeMove(Board board) {
        Scanner scn = super.getScanner();

        System.out.println("Please enter the row where you want to place the symbol");
        int row = scn.nextInt();

        System.out.println("Please enter the col where you want to place the symbol");
        int col = scn.nextInt();

        return new Move(row, col, this);
    }
}
