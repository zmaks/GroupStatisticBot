package com.zheltoukhov.groupstatistic.storage.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("invite")
public class Invite {
    @Id
    private ObjectId objectId;
    private Integer invitedUserId;
    private String invitedUserName;
    private String invitedFullName;
    private Date invitedWhen;

    public Invite(Integer invitedUserId, String invitedUserName, String invitedFullName, Date invitedWhen) {
        this.invitedUserId = invitedUserId;
        this.invitedUserName = invitedUserName;
        this.invitedFullName = invitedFullName;
        this.invitedWhen = invitedWhen;
    }

    public Invite() {
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public Integer getInvitedUserId() {
        return invitedUserId;
    }

    public void setInvitedUserId(Integer invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    public String getInvitedUserName() {
        return invitedUserName;
    }

    public void setInvitedUserName(String invitedUserName) {
        this.invitedUserName = invitedUserName;
    }

    public String getInvitedFullName() {
        return invitedFullName;
    }

    public void setInvitedFullName(String invitedFullName) {
        this.invitedFullName = invitedFullName;
    }

    public Date getInvitedWhen() {
        return invitedWhen;
    }

    public void setInvitedWhen(Date invitedWhen) {
        this.invitedWhen = invitedWhen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invite)) return false;

        Invite invite = (Invite) o;

        return invitedUserId != null ? invitedUserId.equals(invite.invitedUserId) : invite.invitedUserId == null;
    }

    @Override
    public int hashCode() {
        return invitedUserId != null ? invitedUserId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Invite{" +
                "invitedUserId=" + invitedUserId +
                ", invitedUserName='" + invitedUserName + '\'' +
                ", invitedFullName='" + invitedFullName + '\'' +
                ", invitedWhen=" + invitedWhen +
                '}';
    }
}

