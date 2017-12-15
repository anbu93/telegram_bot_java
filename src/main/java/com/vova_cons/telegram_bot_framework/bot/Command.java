package com.vova_cons.telegram_bot_framework.bot;

import java.util.HashMap;

/**
 * Created by anbu 11.12.2017
 * Bot command
 */
public abstract class Command {
    private final String id;
    private HashMap<String, Context> contextMap = new HashMap<>();
    private TelegramBot bot;

    //region initialization
    public Command(String id){
        this.id = id;
    }

    protected void context(String id, Context context){
        contextMap.put(id, context);
    }

    public String getId() {
        return id;
    }

    public void setBot(TelegramBot bot) {
        this.bot = bot;
    }
    //endregion

    //region runtime
    public void onMessage(String message, Session session) throws Exception {
        handle(message, session);
    }

    public void onMessageContext(String message, Session session) throws Exception {
        String contextId = session.getContext();
        if (contextMap.containsKey(contextId)){
            Context context = contextMap.get(contextId);
            context.handle(message, session);
        } else {
            throw new Exception("not found context=" + contextId + " in command=" + id);
        }
    }

    protected abstract void handle(String message, Session session);

    protected void sendAnswer(String message, Session session){
        bot.sendAnswer(session.getId(), message);
    }
    //endregion


    public interface Context {
        void handle(String message, Session session);
    }
}
