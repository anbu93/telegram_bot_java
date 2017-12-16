package com.vova_cons.telegram_bot_framework.conf;

import com.vova_cons.telegram_bot_framework.bot.Command;
import com.vova_cons.telegram_bot_framework.bot.TelegramBot;

/**
 * Created by vova_cons on 16.12.17
 */
public class DefaultTelegramBotBuilder implements TelegramBotBuilder {
    private TelegramBot bot;

    @Override
    public void create(String id, String token) {
        bot = new TelegramBot(id, token);
    }

    @Override
    public void addCommand(String classPath) throws Exception {
        Command command = (Command) Class.forName(classPath).newInstance();
        bot.addCommand(command);
    }

    @Override
    public TelegramBot getBot() {
        return bot;
    }
}
