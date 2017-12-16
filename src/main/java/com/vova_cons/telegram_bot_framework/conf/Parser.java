package com.vova_cons.telegram_bot_framework.conf;

import com.vova_cons.telegram_bot_framework.bot.TelegramBot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by anbu on 11.12.17
 * Bot configuration xml parser.
 * And builder of concrete configured bot TODO 0.2: check SRP
 */
public class Parser {
    private final String filename;
    private TelegramBotBuilder builder;

    public Parser(String filename) {
        this.filename = filename;
        builder = new DefaultTelegramBotBuilder();
    }

    /**
     * Setter for not default bot builder
     * @param builder other bot builder
     */
    public void setBuilder(TelegramBotBuilder builder) {
        this.builder = builder;
    }

    /**
     * Parse configuration file. And create bot.
     * @return created bot
     * @throws Exception if error with creating or parsing configuration
     */
    public TelegramBot parse() throws Exception {
        Element root = parseConfiguration();
        createBot(root);
        createCommands(root);
        return builder.getBot();
    }

    //region parsing
    private Element parseConfiguration() throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(filename);
        return document.getDocumentElement();
    }

    private void createBot(Element root) {
        String id = root.getAttribute("id");
        String token = root.getAttribute("token");
        builder.create(id, token);
    }

    private void createCommands(Element root) throws Exception {
        NodeList commands = root.getElementsByTagName("command");
        for (int i = 0; i < commands.getLength(); i++) {
            Element commandNode = (Element) commands.item(i);
            createCommand(commandNode);
        }
    }

    private void createCommand(Element commandNode) throws Exception {
        String commandClassPath = commandNode.getAttribute("class");
        builder.addCommand(commandClassPath);
    }
    //endregion
}
