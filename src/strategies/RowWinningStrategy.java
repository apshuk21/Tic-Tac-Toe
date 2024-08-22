package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    Map<Integer, Map<Symbol, Integer>> rowMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int value = 0;
        int row = move.getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if (rowMap.containsKey(row)) {
            Map<Symbol, Integer> map = rowMap.get(row);
            if (map.containsKey(symbol)) {
                int currentRowCount = map.get(symbol);
                int updatedRowCount = currentRowCount + 1;
                value = updatedRowCount;
                map.put(symbol, updatedRowCount);

            } else {
                map.put(symbol, 1);
                value = 1;
            }
        } else {
            Map<Symbol, Integer> map = new HashMap<>();
            map.put(symbol, 1);
            value = 1;
            rowMap.put(row, map);
        }
        return value == board.getSize();
    }

    @Override
    public void undoMove(Move move) {
        int row = move.getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (rowMap.containsKey(row)) {
            Map<Symbol, Integer> map = rowMap.get(row);
            if (map.containsKey(symbol)) {
                int currentRowCount = map.get(symbol);
                int updatedRowCount = currentRowCount - 1;
                map.put(symbol, updatedRowCount);
            }
        }
    }
}
