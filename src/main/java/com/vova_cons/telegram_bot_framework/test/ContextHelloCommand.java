package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.bot.Command;
import com.vova_cons.telegram_bot_framework.bot.Session;

public class ContextHelloCommand extends Command {
    private enum Context {
        GetName, GetTown
    }

    public ContextHelloCommand() {
        super("hello");
        context(Context.GetName.name(), this::handleGetName);
        context(Context.GetTown.name(), this::handleGetTown);
    }

    @Override
    protected void handle(String message, Session session) {
        sendAnswer("Please input your name", session);
        session.setContext(Context.GetName.name(), this);
    }

    private void handleGetName(String message, Session session){
        session.setVariable("name", message);
        sendAnswer("Please input your town", session);
        session.setContext(Context.GetTown.name(), this);
    }

    private void handleGetTown(String town, Session session){
        String name = session.getVariable("name", String.class);
        session.removeVariable("name");
        String message = "Hello, " + name + " from " + town;
        sendAnswer(message, session);
        session.clearContext();
    }
}
