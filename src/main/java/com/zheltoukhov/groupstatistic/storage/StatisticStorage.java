package com.zheltoukhov.groupstatistic.storage;

import com.zheltoukhov.groupstatistic.storage.entities.Invite;
import com.zheltoukhov.groupstatistic.storage.entities.Inviter;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;

import java.util.Collection;
import java.util.Set;

public interface StatisticStorage {

    TelegramGroup storeGroup(TelegramGroup telegramGroup);
    TelegramGroup getGroup(Long groupChatId);
    Collection<TelegramGroup> getGroups();

    Inviter storeInviter(Inviter inviter);
    Inviter getInviter(Integer inviterUserId);
    Collection<Inviter> getInviters();

    /*Invite storeInvite(Invite invite);
    Invite getInvite();*/

}
