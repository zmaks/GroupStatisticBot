package com.zheltoukhov.groupstatistic.bot;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class GroupStatisticBot extends TelegramLongPollingBot {

    private final String botToken = ConfigProperties.getProperty("bot-token");
    private final String botUserName = ConfigProperties.getProperty("bot-user-name");

    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            if (update.getMessage().hasText())
                System.out.println(update.getMessage().getText());

        }

        CommandsHandler.INSTANCE.handle(update, this::callback);

    }

    private void callback(PartialBotApiMethod method) {

    }

    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }
}
