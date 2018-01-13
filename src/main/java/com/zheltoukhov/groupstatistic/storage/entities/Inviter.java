package com.zheltoukhov.groupstatistic.storage.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.ArrayList;
import java.util.List;

@Entity("inviter")
public class Inviter {
    @Id
    private ObjectId objectId;
    private Integer userId;
    @Property("group_chat_id")
    private Long groupId;
    private String userName;
    private String fullName;
    private List<Invite> invites;

    public Inviter(Integer userId, Long groupId, String userName, String fullName) {
        this.userId = userId;
        this.groupId = groupId;
        this.userName = userName;
        this.fullName = fullName;
    }

    public Inviter() {
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addInvite(Invite invite) {
        if (this.invites == null)
            this.invites = new ArrayList<>();
        this.invites.add(invite);
    }

    public void addInvites(List<Invite> invites) {
        if (this.invites == null)
            this.invites = new ArrayList<>();
        this.invites.addAll(invites);
    }

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }

    public Long getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inviter)) return false;

        Inviter inviter = (Inviter) o;

        if (userId != null ? !userId.equals(inviter.userId) : inviter.userId != null) return false;
        return groupId != null ? groupId.equals(inviter.groupId) : inviter.groupId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Inviter{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", invites=" + invites +
                '}';
    }
}
