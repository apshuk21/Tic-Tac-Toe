package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    Map<Integer, Map<Symbol, Integer>> columnMap = new HashMap<>();


    @Override
    public boolean checkWinner(Board board, Move move) {
        int value = 0;
        int column = move.getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (columnMap.containsKey(column)) {
            Map<Symbol, Integer> map = columnMap.get(column);
            if (map.containsKey(symbol)) {
                int currentColumnCount = map.get(symbol);
                int updatedColumnCount = currentColumnCount + 1;
                value = updatedColumnCount;
                map.put(symbol, updatedColumnCount);
            } else {
                map.put(symbol, 1);
                value = 1;
            }
        } else {
            Map<Symbol, Integer> map = new HashMap<>();
            map.put(symbol, 1);
            value = 1;
            columnMap.put(column, map);
        }
        return value == board.getSize();
    }

    @Override
    public void undoMove(Move move) {
        int column = move.getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (columnMap.containsKey(column)) {
            Map<Symbol, Integer> map = columnMap.get(column);
            if (map.containsKey(symbol)) {
                int currentColumnCount = map.get(symbol);
                int updatedColumnCount = currentColumnCount - 1;
                map.put(symbol, updatedColumnCount);
            }
        }
    }
}
