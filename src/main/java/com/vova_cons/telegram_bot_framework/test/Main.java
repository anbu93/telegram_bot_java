package com.vova_cons.telegram_bot_framework.test;

import com.vova_cons.telegram_bot_framework.TelegramBotCore;

public class Main {
    public static void main(String[] args){
        try {
            TelegramBotCore core = new TelegramBotCore();
            core.init("conf/shedule_helper_bot.xml");
            System.out.println("Started!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
