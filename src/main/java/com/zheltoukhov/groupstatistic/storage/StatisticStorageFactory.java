package com.zheltoukhov.groupstatistic.storage;

import com.zheltoukhov.groupstatistic.ConfigProperties;
import com.zheltoukhov.groupstatistic.storage.impl.MapStatisticStorageImpl;
import com.zheltoukhov.groupstatistic.storage.impl.MongoStatisticStorageImpl;

public class StatisticStorageFactory {
    private StatisticStorageFactory(){}

    public synchronized static StatisticStorage getStatisticStorage() {
        boolean useMongo = Boolean.valueOf(ConfigProperties.getProperty("use-mongo"));
        return useMongo ? MongoStatisticStorageImpl.getInstance() : MapStatisticStorageImpl.INSTANCE;
    }
}
