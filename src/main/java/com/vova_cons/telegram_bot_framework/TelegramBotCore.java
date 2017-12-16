package com.vova_cons.telegram_bot_framework;

import com.vova_cons.telegram_bot_framework.bot.TelegramBot;
import com.vova_cons.telegram_bot_framework.conf.Parser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by anbu on 11.12.17
 * core of framework
 */
public class TelegramBotCore {
    private TelegramBot bot;

    /**
     * bot initialization (parsing, building and run)
     * @param parser configuration parser
     * @throws Exception with can't started bot
     */
    public void init(Parser parser) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        bot = parser.parse();
        try {
            botapi.registerBot(bot);
        } catch (TelegramApiException e) {
            //TODO 0.2: error formatting
            e.printStackTrace();
        }
    }
}
