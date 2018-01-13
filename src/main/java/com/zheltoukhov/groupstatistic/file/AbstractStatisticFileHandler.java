package com.zheltoukhov.groupstatistic.file;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import com.zheltoukhov.groupstatistic.storage.StatisticStorage;
import com.zheltoukhov.groupstatistic.storage.StatisticStorageFactory;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;

import java.io.File;

public abstract class AbstractStatisticFileHandler implements StatisticFileHandler {
    protected final StatisticStorage storage = StatisticStorageFactory.getStatisticStorage();

    protected void createDirectory(String path) {
        new File(path).mkdirs();
    }

    protected File getFile(TelegramGroup group) {
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder
                .append(ConfigProperties.getProperty("file-path")).append("/")
                .append(group.getName()).append("_")
                .append(group.getChatId())
                .append(getSuffix());
        return new File(nameBuilder.toString());
    }

    protected abstract String getSuffix();
}
