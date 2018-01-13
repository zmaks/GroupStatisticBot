package com.zheltoukhov.groupstatistic.commands;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import com.zheltoukhov.groupstatistic.storage.StatisticStorage;
import com.zheltoukhov.groupstatistic.storage.StatisticStorageFactory;
import org.telegram.telegrambots.api.objects.User;

public abstract class AbstractBotCommand implements BotCommand {

    protected StatisticStorage storage = StatisticStorageFactory.getStatisticStorage();

    protected static final Integer INVITED_BY_LINK_DEFAULT_ID = 1;
    protected static final String INVITED_BY_LINK_DEFAULT_NAME = ConfigProperties.getProperty("by-link-name");

    protected String getUserName(User user) {
        StringBuilder nameBuilder = new StringBuilder();
        if (user.getFirstName() != null)
            nameBuilder.append(user.getFirstName()).append(" ");
        if (user.getLastName() != null) {
            nameBuilder.append(user.getLastName());
        }
        return nameBuilder.toString();
    }

}
