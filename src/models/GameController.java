package models;

import strategies.WinningStrategy;

import java.util.List;

/**
 * 1. Start the game: Create the game controller, take the inputs, validate the inputs, and create the game
 * while the game state is IN_PROGRESS
 * 2. Display the board
 * 3. Make the move: input, make the move, and update the state
 * 4. Check the game state
 * if the game state is SUCCESS
 * 5. Get the winner and display
 * else if the game  state is DRAW
 * 6. declare the draw
 */

public class GameController {


    public Game startGame(int size, List<Player> players, List<WinningStrategy> winningStrategies) {

        return Game.createBuilder()
                .Size(size)
                .Players(players)
                .WinningStrategies(winningStrategies)
                .build();
    }

    public GameState checkGameState(Game game) {
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void Undo(Player player) {

    }

    public void display(Game game) {
        game.displayBoard();
    }
}
