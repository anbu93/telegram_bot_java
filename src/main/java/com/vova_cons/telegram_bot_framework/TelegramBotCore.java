package com.vova_cons.telegram_bot_framework;

import com.vova_cons.telegram_bot_framework.bot.TelegramBot;
import com.vova_cons.telegram_bot_framework.conf.Parser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramBotCore {
    private TelegramBot bot;

    public TelegramBotCore() {

    }

    public void init(String configurationFile) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        Parser parser = new Parser(configurationFile);
        bot = parser.parse();
        try {
            botapi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
