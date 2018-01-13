package com.zheltoukhov.groupstatistic.storage.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("telegram_group")
public class TelegramGroup {
    @Id
    private ObjectId objectId;
    private Long chatId;
    private String name;
    private Boolean needUpdateFile;

    public TelegramGroup(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }

    public TelegramGroup() {
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNeedUpdateFile() {
        return needUpdateFile;
    }

    public void setNeedUpdateFile(Boolean needUpdateFile) {
        this.needUpdateFile = needUpdateFile;
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
