package com.zheltoukhov.groupstatistic.job;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import com.zheltoukhov.groupstatistic.file.impl.ExcelStatisticFileHandlerImpl;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileUpdaterJob implements GroupStatisticJob {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    ExcelStatisticFileHandlerImpl fileHandler = new ExcelStatisticFileHandlerImpl();
    private static final long FILES_UPDATE_DELAY = getFileUpdatesDelay();

    @Override
    public void run() {
        executorService.scheduleWithFixedDelay(() -> {
            try {
                fileHandler.writeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, FILES_UPDATE_DELAY, TimeUnit.SECONDS);
    }

    private static long getFileUpdatesDelay() {
        return Long.parseLong(ConfigProperties.getProperty("file-updates-delay"));
    }
}
