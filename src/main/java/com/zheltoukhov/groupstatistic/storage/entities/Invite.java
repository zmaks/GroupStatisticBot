package com.zheltoukhov.groupstatistic.storage.entities;

import java.util.Date;

public class Invite {

    private final Integer invitedUserId;
    private final String invitedUserName;
    private final String invitedFullName;
    private final Date invitedWhen;

    public Invite(Integer invitedUserId, String invitedUserName, String invitedFullName, Date invitedWhen) {
        this.invitedUserId = invitedUserId;
        this.invitedUserName = invitedUserName;
        this.invitedFullName = invitedFullName;
        this.invitedWhen = invitedWhen;
    }

    public Integer getInvitedUserId() {
        return invitedUserId;
    }

    public String getInvitedUserName() {
        return invitedUserName;
    }

    public String getInvitedFullName() {
        return invitedFullName;
    }

    public Date getInvitedWhen() {
        return invitedWhen;
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

