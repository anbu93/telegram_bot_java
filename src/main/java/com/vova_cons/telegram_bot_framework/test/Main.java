package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.TelegramBotCore;

/**
 * Created by anbu on 11.12.17
 * main demo class
 */
public class Main {
    public static void main(String[] args){
        try {
            //creating core
            TelegramBotCore core = new TelegramBotCore();
            //set configuration file and init bot
            core.init("conf/shedule_helper_bot.xml");
            //set database saving file
            core.setDatabaseFile("conf/shedule_helper_bot.db");
            //congratulation, bot are started!
            System.out.println("Started!");
        } catch (Exception e){
            //if bot can't started
            e.printStackTrace();
        }
    }
}
