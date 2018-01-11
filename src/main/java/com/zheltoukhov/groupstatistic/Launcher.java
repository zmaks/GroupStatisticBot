package com.zheltoukhov.groupstatistic;

import com.zheltoukhov.groupstatistic.bot.GroupStatisticBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Launcher {
    public static void main(String[] args) {
        initBot();
    }

    private static  void  initBot() {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new GroupStatisticBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
