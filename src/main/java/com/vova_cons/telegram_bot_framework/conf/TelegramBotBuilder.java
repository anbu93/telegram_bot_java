package com.vova_cons.telegram_bot_framework.conf;

import com.vova_cons.telegram_bot_framework.bot.TelegramBot;

/**
 * created by vova_cons on 16.12.17
 * Interface for bot configuration builder
 */
public interface TelegramBotBuilder {
    /**
     * Create telegram bot instance
     * @param id telegram bot identifier
     * @param token bot access token
     */
    void create(String id, String token);

    /**
     * add new command class (using pattern Command[GoF])
     * @param classPath command class path
     * @throws Exception if class from classPath not crated
     */
    void addCommand(String classPath) throws Exception;

    /**
     * Getter for created telegram bot
     * @return telegram bot instance
     */
    TelegramBot getBot();
}
