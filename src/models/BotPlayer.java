package models;

import strategies.BotPlayingStrategy;
import strategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player {
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy strategy;


    public BotPlayer(int id, String name, PlayerType playerType, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(id, name, playerType, symbol);
        this.difficultyLevel = difficultyLevel;
    }

    public BotPlayingStrategy createStrategy(BotDifficultyLevel difficultyLevel) {
        return BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        BotPlayingStrategy strategy = createStrategy(difficultyLevel);
        Move move = strategy.makeMove(board);
        move.setPlayer(this);

        return move;
    }
}
