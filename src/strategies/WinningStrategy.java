package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public interface WinningStrategy {

    public boolean checkWinner(Board board, Move move);
    public void undoMove(Move move);
}
