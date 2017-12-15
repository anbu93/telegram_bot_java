package com.vova_cons.telegram_bot_framework.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class TelegramBot extends TelegramLongPollingBot {
    private String id;
    private String token;
    private HashMap<Long, Session> sessionsMap = new HashMap<>();
    private HashMap<String, Command> commandMap = new HashMap<>();

    //region initialization
    public TelegramBot(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public void addCommand(Command command){
        command.setBot(this);
        commandMap.put(command.getId(), command);
    }
    //endregion

    //region receive
    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            long id = message.getChatId();
            String text = message.getText();
            Session session = getSession(id);
            String contextId = session.getContext();
            if (contextId != null) {
                handleContext(contextId, text, session);
            } else {
                handleCommand(text, session);
            }
        } catch(Exception e){
            long id = update.getMessage().getChatId();
            sendAnswer(id, "Error: " + e.getMessage());
        }
    }

    private Session getSession(long id){
        if (!sessionsMap.containsKey(id))
            register(id);
        return sessionsMap.get(id);
    }

    private void register(long id) {
        Session session = new Session(id);
        sessionsMap.put(id, session);
    }

    private void handleContext(String contextId, String message, Session session) throws Exception {
        Command command = session.getContextCommand();
        if (command == null) {
            throw new Exception("context=" + contextId + " not contains command (is null)");
        } else {
            command.onMessageContext(message, session);
        }
    }

    private void handleCommand(String text, Session session) throws Exception {
        String[] tokens = text.split(" ");
        String commandId = tokens[0].toLowerCase();
        Command command = commandMap.get(commandId);
        if (command == null){
            command = commandMap.get(commandId.substring(1));
        }
        if (command == null) {
            throw new Exception("not found command for message \'" + text + "\'");
        }
        command.onMessage(text, session);
    }
    //endregion


    //region send
    public void sendAnswer(Long id, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(id);
        s.setText(text);
        try {
            sendApiMethod(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    //endregion

    @Override
    public String getBotUsername() {
        return id;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
