package com.zheltoukhov.groupstatistic.bot;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class GroupStatisticBot extends TelegramLongPollingBot {

    private final String botToken = ConfigProperties.getProperty("bot.token");
    private final String botUserName = ConfigProperties.getProperty("bot.user-name");

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                try {
                    execute(new SendMessage(update.getMessage().getChatId(), "ok"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

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
