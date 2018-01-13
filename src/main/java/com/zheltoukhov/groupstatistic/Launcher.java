package com.zheltoukhov.groupstatistic;

import com.zheltoukhov.groupstatistic.bot.GroupStatisticBot;
import com.zheltoukhov.groupstatistic.job.FileUpdaterJob;
import com.zheltoukhov.groupstatistic.storage.entities.Invite;
import com.zheltoukhov.groupstatistic.storage.entities.Inviter;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;
import com.zheltoukhov.groupstatistic.storage.impl.MongoStatisticStorageImpl;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws IOException {
        initBot();
        runJobs();
    }

    private static  void  initBot() {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new GroupStatisticBot());
            System.out.println("Bot is started");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static void runJobs() {
        new FileUpdaterJob().run();
        System.out.println("File Updater Job is started");

    }
}
