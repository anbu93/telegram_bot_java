package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.bot.Command;
import com.vova_cons.telegram_bot_framework.bot.Session;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    protected void handle(String message, Session session) {
        sendAnswer("this is help route", session);
    }
}
