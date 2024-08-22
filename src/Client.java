import models.*;
import strategies.ColumnWinningStrategy;
import strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1. Create all models
 * 2. Create your controller and basic interaction with the client
 * 3. Identify the parameters required from the client to start the game.
 * 4. Validate the user inputs before starting the game. Use of builder design pattern
 * HW
 * Create the row strategies
 */
public class Client {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        GameController gameController = new GameController();

        Player player1 = new HumanPlayer(1, "Vapor", PlayerType.HUMAN, new Symbol('X'));
        Player player2 = new BotPlayer(1, "Bot", PlayerType.BOT, new Symbol('0'), BotDifficultyLevel.EASY);
        List<Player> players = new ArrayList<Player>(List.of(player1, player2));

        // Start the game
        Game game1 = gameController.startGame(3, players, List.of(new RowWinningStrategy(), new ColumnWinningStrategy()));

        // Display the game
        gameController.display(game1);

        // make the move
        while (gameController.checkGameState(game1).equals(GameState.IN_PROGRESS)) {
            gameController.makeMove(game1);
            gameController.display(game1);

            System.out.println("Do you want to undo? [Y/N]");
            String undoInput = scanner.nextLine().toLowerCase();

            if (undoInput.equals("y")) {
                gameController.undo(game1);
            }
        }

        // Check the game state
        if (gameController.checkGameState(game1).equals(GameState.SUCCESS)) {
            System.out.println(gameController.getWinner(game1).getName() + " won the game");
        } else if (gameController.checkGameState(game1).equals(GameState.DRAW)) {
            System.out.println("Game results in a draw");
        }

    }
}