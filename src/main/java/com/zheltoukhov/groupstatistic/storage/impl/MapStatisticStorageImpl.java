package com.zheltoukhov.groupstatistic.storage.impl;

import com.zheltoukhov.groupstatistic.storage.StatisticStorage;
import com.zheltoukhov.groupstatistic.storage.entities.Inviter;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum MapStatisticStorageImpl implements StatisticStorage {
    INSTANCE {
        private Map<Long, TelegramGroup> groupMap = new HashMap<>();
        private Map<Integer, Inviter> inviterMap = new HashMap<>();

        @Override
        public TelegramGroup storeGroup(TelegramGroup telegramGroup) {
            groupMap.put(telegramGroup.getChatId(), telegramGroup);
            return telegramGroup;
        }

        @Override
        public TelegramGroup getGroup(Long groupChatId) {
            return groupMap.get(groupChatId);
        }

        @Override
        public Collection<TelegramGroup> getGroups() {
            return groupMap.values();
        }

        @Override
        public Inviter storeInviter(Inviter inviter) {
            inviterMap.put(inviter.getUserId(), inviter);
            return inviter;
        }

        @Override
        public Inviter getInviter(Integer inviterUserId) {
            return inviterMap.get(inviterUserId);
        }

        @Override
        public Collection<Inviter> getInviters() {
            return inviterMap.values();
        }
    }


}
