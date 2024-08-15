package strategies;

import models.BotDifficultyLevel;

import java.util.Objects;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficulty) {
        BotPlayingStrategy strategy = null;
        if (Objects.requireNonNull(difficulty) == BotDifficultyLevel.EASY) {
            strategy = new EasyBotPlayingStrategy();
        }

        return strategy;
    }
}
