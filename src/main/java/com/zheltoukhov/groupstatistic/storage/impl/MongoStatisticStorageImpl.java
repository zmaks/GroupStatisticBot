package com.zheltoukhov.groupstatistic.storage.impl;

import com.zheltoukhov.groupstatistic.storage.MorphiaService;
import com.zheltoukhov.groupstatistic.storage.StatisticStorage;
import com.zheltoukhov.groupstatistic.storage.entities.Inviter;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import java.util.Collection;

public class MongoStatisticStorageImpl implements StatisticStorage {

    private static MongoStatisticStorageImpl instance;
    private static MorphiaService morphiaService;
    private static Datastore ds;

    private MongoStatisticStorageImpl() {
        morphiaService = MorphiaService.getInstance();
        ds = morphiaService.getDatastore();
    }

    @Override
    public TelegramGroup storeGroup(TelegramGroup telegramGroup) {
        Key<TelegramGroup> key = ds.save(telegramGroup);
        telegramGroup.setObjectId((ObjectId) key.getId());
        return telegramGroup;
    }

    @Override
    public TelegramGroup getGroup(Long groupChatId) {
        return ds.find(TelegramGroup.class).filter("chatId", groupChatId).get();
    }

    @Override
    public Collection<TelegramGroup> getGroups() {
        return ds.find(TelegramGroup.class).asList();
    }

    @Override
    public Inviter storeInviter(Inviter inviter) {
        Key<Inviter> inviterKey = ds.save(inviter);
        inviter.setObjectId((ObjectId) inviterKey.getId());
        return inviter;
    }

    @Override
    public Inviter getInviter(Integer inviterUserId) {
        return ds.find(Inviter.class).filter("userId", inviterUserId).get();
    }

    @Override
    public Collection<Inviter> getInviters() {
        return ds.find(Inviter.class).asList();
    }

    public static MongoStatisticStorageImpl getInstance() {
        if (instance == null) {
            instance = new MongoStatisticStorageImpl();
        }
        return instance;
    }
}
