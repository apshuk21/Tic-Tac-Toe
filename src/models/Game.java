package models;

import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    private Game(Builder builder) {
        board = new Board(builder.size);
        players = builder.players;
        winningStrategies = builder.winningStrategies;
        winner = null;
        nextPlayerIndex = 0;
        moves = new ArrayList<>();
        gameState = GameState.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void displayBoard() {
        board.display();
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public boolean validateMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();

        // return false if row and cell are out of bound
        if (!(row >= 0 && row <= board.getSize() - 1 && col >= 0 && col <= board.getSize() - 1)) {
            return false;
        }

        // return true if cell is empty
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public boolean checkWinner(Move move) {
        for (WinningStrategy strategy : winningStrategies) {
            if (strategy.checkWinner(board, move)) {
                return true;
            }
        }

        return false;
    }

    public void makeMove() {
        // Get the current player
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("It's the " + currentPlayer.getName() + "'s turn. Please make the move!");

        Move move = currentPlayer.makeMove(board);

        // Validate the move
        if (!validateMove(move)) {
            System.out.println("Invalid move!");
            return;
        }

        int row = move.getRow();
        int col = move.getCol();

        Cell cellToChange = board.getGrid().get(row).get(col);
        cellToChange.setSymbol(currentPlayer.getSymbol());
        cellToChange.setCellState(CellState.FILLED);

        // Link the cell with the Move
        move.setGridCellLinkedWithMove(cellToChange);

        // Add the move to the list
        moves.add(move);

        nextPlayerIndex++;

        nextPlayerIndex %= players.size();

        // we need to confirm if there is a change in the Game State
        if (checkWinner(move)) {
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);
        } else if (moves.size() == board.getSize()*board.getSize()) {
            setWinner(null);
            setGameState(GameState.DRAW);
        }
    }

    public static class Builder {
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder Size(int size) {
            this.size = size;
            return this;
        }

        public Builder Players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder WinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validate() {
            // 1. Player size should match the size - 1
            if (players.size() != size - 1) {
                throw new RuntimeException("Invalid players count");
            }

            // 2. Only one bot is allowed
            int botCount = 0;

            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }

            if (botCount > 1) {
                throw new RuntimeException("Invalid bots count");
            }

            // 3. Every player should have a unique symbol
            Set<Character> symbolSet = new HashSet<>();

            for (Player player : players) {
                Symbol symbol = player.getSymbol();
                if (symbolSet.contains(symbol.getSymbol())) {
                    throw new RuntimeException("Duplicate symbol");
                }
                symbolSet.add(symbol.getSymbol());
            }

        }

        public Game build() {
            validate();
            return new Game(this);
        }
    }
}
