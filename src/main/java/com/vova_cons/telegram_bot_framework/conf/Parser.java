package com.vova_cons.telegram_bot_framework.conf;

import com.vova_cons.telegram_bot_framework.bot.Command;
import com.vova_cons.telegram_bot_framework.bot.TelegramBot;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by anbu on 11.12.17
 * Bot configuration xml parser.
 * And builder of concrete configured bot TODO 0.2: check SRP
 */
public class Parser {
    private final String filename;
    private TelegramBot bot;

    public Parser(String filename) {
        this.filename = filename;
    }

    //TODO 0.1: refactor it
    public TelegramBot parse() throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(filename);
        Element root = document.getDocumentElement();
        String id = root.getAttribute("id");
        String token = root.getAttribute("token");
        bot = new TelegramBot(id, token);
        NodeList commands = root.getElementsByTagName("command");
        for (int i = 0; i < commands.getLength(); i++) {
            Element commandNode = (Element) commands.item(i);
            String commandClassPath = commandNode.getAttribute("class");
            Command command = (Command) Class.forName(commandClassPath).newInstance();
            bot.addCommand(command);
        }
        return bot;
    }
}
