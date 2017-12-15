package com.vova_cons.telegram_bot_framework.bot;

import java.util.HashMap;

/**
 * Created by anbu on 11.12.17
 * Telegram bot user session
 */
public class Session {
    private final long id;
    private Command command;
    private String context = null;
    private HashMap<String, Object> variableMap = new HashMap<>();

    /**
     * user session creating
     * @param id user id in long
     */
    Session(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    //region variables
    /**
     * save user variable
     * @param id variable identifier
     * @param value variable value
     */
    public void setVariable(String id, Object value){
        variableMap.put(id, value);
    }

    /**
     * remove variable from session
     * @param id variable identifier
     */
    public void removeVariable(String id){
        if (variableMap.containsKey(id))
            variableMap.remove(id);
    }

    /**
     * get variable from session
     * @param id variable identifier
     * @param castClass variable class
     * @param <T> variable class
     * @return variable if exists in session, or null
     */
    public <T> T getVariable(String id, Class<T> castClass){
        if (variableMap.containsKey(id)) {
            Object obj = variableMap.get(id);
            return (T) obj;
        }
        return null;
    }

    /**
     * get variable from session
     * @param id variable identifier
     * @param defaultValue default returned value if variable not exists in session
     * @param castClass variable class
     * @param <T> variable class
     * @return variable if exists in session, or defaultValue
     */
    public <T> T getVariable(String id, T defaultValue, Class<T> castClass){
        T result = getVariable(id, castClass);
        if (result == null){
            return defaultValue;
        }
        return defaultValue;
    }
    //endregion


    //region context
    /**
     * get session context
     * @return context identifier (String) or null if no context
     */
    public String getContext() {
        return context;
    }

    /**
     * get context command
     * @return command or null if context clear
     */
    public Command getContextCommand() {
        return command;
    }

    /**
     * set context (Warning: not null checking!)
     * @param context context identifier
     * @param command context command
     */
    public void setContext(String context, Command command){
        this.context = context;
        this.command = command;
    }

    /**
     * clear context (set null for command and id)
     */
    public void clearContext() {
        setContext(null, null);
    }
    //endregion
}
