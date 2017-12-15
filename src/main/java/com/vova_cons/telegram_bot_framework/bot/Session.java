package com.vova_cons.telegram_bot_framework.bot;

import java.util.HashMap;

/**
 * Created by anbu 11.12.2017
 * Telegram bot user session
 */
public class Session {
    private final long id;
    private Command command;
    private String context = null;
    private HashMap<String, Object> variableMap = new HashMap<>();

    public Session(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    //region variables
    public void setVariable(String id, Object value){
        variableMap.put(id, value);
    }

    public void removeVariable(String id){
        if (variableMap.containsKey(id))
            variableMap.remove(id);
    }

    public <T> T getVariable(String id, Class<T> castClass){
        if (variableMap.containsKey(id)) {
            Object obj = variableMap.get(id);
            return (T) obj;
        }
        return null;
    }

    public <T> T getVariable(String id, T defaultValue, Class<T> castClass){
        T result = getVariable(id, castClass);
        if (result == null){
            return defaultValue;
        }
        return defaultValue;
    }
    //endregion

    //region context
    public String getContext() {
        return context;
    }

    public Command getContextCommand() {
        return command;
    }

    public void setContext(String context, Command command){
        this.context = context;
        this.command = command;
    }

    public void clearContext() {
        setContext(null, null);
    }
    //endregion
}
