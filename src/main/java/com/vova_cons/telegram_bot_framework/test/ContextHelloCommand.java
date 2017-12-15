package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.bot.Command;
import com.vova_cons.telegram_bot_framework.bot.Session;

/**
 * Created by anbu on 11.12.17
 */
public class ContextHelloCommand extends Command {
    //command contexts enum (for not using plot Strings)
    private enum Context {
        GetName, //bot need user name
        GetTown //bot need user town
    }

    public ContextHelloCommand() {
        super("hello"); //set id
        //set first context.
        //set it identifer from enum name, and link handler to this private method
        context(Context.GetName.name(), this::handleGetName);
        //and set second context
        context(Context.GetTown.name(), this::handleGetTown);
    }

    @Override
    protected void handle(String message, Session session) {
        //we receive "/hello"
        //send message to user
        sendAnswer("Please input your name", session);
        //and set context for this session (context id from enum name, may be plot String!)
        session.setContext(Context.GetName.name(), this);
    }

    private void handleGetName(String name, Session session){
        //we receive user name in name, save it in session variables
        session.setVariable("name", name);
        //send answer (for user town)
        sendAnswer("Please input your town", session);
        //and change context
        session.setContext(Context.GetTown.name(), this);
    }

    private void handleGetTown(String town, Session session){
        //get user name from session variables
        String name = session.getVariable("name", String.class);
        //remove it variable from session (not collect trash variables)
        session.removeVariable("name");
        //generating answer message
        String message = "Hello, " + name + " from " + town;
        //send generated message to user
        sendAnswer(message, session);
        //and clear context (command finish working)
        session.clearContext();
    }
}
