package com.zheltoukhov.groupstatistic.commands;

import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

import java.util.function.Consumer;

public interface BotCommand {
     boolean execute(Update update, Consumer<? extends PartialBotApiMethod> callback);
}
