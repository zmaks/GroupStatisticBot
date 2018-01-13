package com.zheltoukhov.groupstatistic.storage;

import com.zheltoukhov.groupstatistic.storage.impl.MapStatisticStorageImpl;

public class StatisticStorageFactory {
    private StatisticStorageFactory(){}

    public static StatisticStorage getStatisticStorage() {
        return MapStatisticStorageImpl.INSTANCE;
    }
}
