package com.zheltoukhov.groupstatistic.storage.entities;

import java.util.ArrayList;
import java.util.List;

public class TelegramGroup {
    private final Long chatId;
    private String name;

    public TelegramGroup(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelegramGroup)) return false;

        TelegramGroup that = (TelegramGroup) o;

        return chatId != null ? chatId.equals(that.chatId) : that.chatId == null;
    }

    @Override
    public int hashCode() {
        return chatId != null ? chatId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TelegramGroup{" +
                "chatId=" + chatId +
                ", name='" + name + '\'' +
                '}';
    }
}
