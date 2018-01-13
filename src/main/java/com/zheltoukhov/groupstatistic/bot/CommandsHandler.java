package com.zheltoukhov.groupstatistic.bot;

import com.zheltoukhov.groupstatistic.commands.BotCommand;
import com.zheltoukhov.groupstatistic.commands.NewGroupMemberCommand;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public enum CommandsHandler {
    INSTANCE;

    public static final List<BotCommand> COMMANDS = new ArrayList<>();

    static {
        COMMANDS.add(new NewGroupMemberCommand());
    }

    public void handle(Update update, Consumer<? extends PartialBotApiMethod> callback) {
        for (BotCommand command : COMMANDS) {
            if (command.execute(update, callback)) break;
        }
    }
}
