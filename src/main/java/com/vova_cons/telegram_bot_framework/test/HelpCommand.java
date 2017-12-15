package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.bot.Command;
import com.vova_cons.telegram_bot_framework.bot.Session;

/**
 * Created by anbu on 11.12.17
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help"); //set command id
        //user can call this command in chat with '/help' message
    }

    @Override
    protected void handle(String message, Session session) {
        //handle incoming message (this is message forever start with '/help ')
        sendAnswer("this is help route", session); //send simple answer to user for test
    }
}
