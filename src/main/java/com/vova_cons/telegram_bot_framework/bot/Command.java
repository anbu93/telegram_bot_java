package com.vova_cons.telegram_bot_framework.bot;

import java.util.HashMap;

/**
 * Created by anbu on 11.12.17
 * Bot command [GoF]
 */
public abstract class Command {
    private final String id;
    private HashMap<String, Context> contextMap = new HashMap<>();
    private TelegramBot bot;

    //region initialization

    /**
     * Create command instance
     * @param id command identifier (by this id called user in chat)
     */
    public Command(String id){
        this.id = id;
    }

    /**
     * create new command context
     * @param id context identifier
     * @param context context handler
     */
    protected void context(String id, Context context){
        contextMap.put(id, context);
    }

    String getId() {
        return id;
    }

    void setBot(TelegramBot bot) {
        this.bot = bot;
    }
    //endregion

    //region runtime

    /**
     * handler for message income for this command
     * @param message message text
     * @param session user session, who send this message
     * @throws Exception thrown exception if handler error
     */
    void onMessage(String message, Session session) throws Exception {
        handle(message, session);
    }

    /**
     * handler for context message. Context id can be found in session
     * @param message message text
     * @param session user session
     * @throws Exception throw exception if context not found, or if context handler error
     */
    void onMessageContext(String message, Session session) throws Exception {
        String contextId = session.getContext();
        if (contextMap.containsKey(contextId)){
            Context context = contextMap.get(contextId);
            context.handle(message, session);
        } else {
            //TODO 0.2: error formatting
            throw new Exception("not found context=" + contextId + " in command=" + id);
        }
    }

    /**
     * Concrete command handler (implement this method for add command behaviour)
     * @param message message text
     * @param session user session
     */
    protected abstract void handle(String message, Session session);

    /**
     * Send answer message from bot to user
     * @param message answer message text
     * @param session user session, who give message
     */
    protected void sendAnswer(String message, Session session){
        bot.sendAnswer(session.getId(), message);
    }
    //endregion

    /**
     * interface for command context
     * //TODO 0.1: refactor. move to another place and rename
     */
    public interface Context {
        /**
         * context handler
         * @param message message text
         * @param session user session
         * @throws Exception throw error if handler error
         */
        void handle(String message, Session session) throws Exception;
    }
}
