package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.TelegramBotCore;
import com.vova_cons.telegram_bot_framework.conf.Parser;

/**
 * Created by anbu on 11.12.17
 * main demo class
 */
public class Main {
    public static void main(String[] args){
        try {
            //creating core
            TelegramBotCore core = new TelegramBotCore();
            //parse configuration parser creating
            Parser parser = new Parser("conf/test_bot.xml");
            //if neccessary you can set other bot builder object:
            //parser.setBuilder(otherBuilder);
            //initialization for bot
            core.init(parser);
            //congratulation, bot are started!
            System.out.println("Started!");
        } catch (Exception e){
            //if bot can't started
            e.printStackTrace();
        }
    }
}
