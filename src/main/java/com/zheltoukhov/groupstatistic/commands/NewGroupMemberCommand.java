package com.zheltoukhov.groupstatistic.commands;

import com.zheltoukhov.groupstatistic.storage.entities.Invite;
import com.zheltoukhov.groupstatistic.storage.entities.Inviter;
import com.zheltoukhov.groupstatistic.storage.entities.TelegramGroup;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NewGroupMemberCommand extends AbstractBotCommand {

    @Override
    public boolean execute(Update update, Consumer<? extends PartialBotApiMethod> callback) {
        if (!update.hasMessage() || update.getMessage().getNewChatMembers() == null
                || update.getMessage().getNewChatMembers().isEmpty()) {
            return false;
        }

        Chat groupChat = update.getMessage().getChat();
        User inviterTelegramUser = update.getMessage().getFrom();
        List<User> invitedTelegramUsers = update.getMessage().getNewChatMembers();

        TelegramGroup group = getTelegramGroup(groupChat);

        Inviter inviter = inviterTelegramUser == null ?
                getByLinkInviter(groupChat.getInviteLink(), group.getChatId()) :
                getUserInviter(inviterTelegramUser, group.getChatId());

        List<Invite> invites = invitedTelegramUsers
                .stream()
                .map(u -> new Invite(u.getId(), u.getUserName(), getUserName(u), new Date()))
                .collect(Collectors.toList());

        inviter.addInvites(invites);
        storage.storeInviter(inviter);

        return true;
    }

    private TelegramGroup getTelegramGroup(Chat groupChat) {
        TelegramGroup telegramGroup = storage.getGroup(groupChat.getId());

        if (telegramGroup == null) {
            telegramGroup = new TelegramGroup(-1*groupChat.getId(), groupChat.getTitle());
            telegramGroup = storage.storeGroup(telegramGroup);
        }

        return telegramGroup;
    }

    private Inviter getByLinkInviter(String inviteLink, Long groupChatId) {
        Inviter inviter = storage.getInviter(INVITED_BY_LINK_DEFAULT_ID);
        if (inviter == null) {
            inviter = new Inviter(INVITED_BY_LINK_DEFAULT_ID, groupChatId, inviteLink, INVITED_BY_LINK_DEFAULT_NAME);
        }
        return inviter;
    }

    private Inviter getUserInviter(User user, Long groupChatId) {
        Inviter inviter = storage.getInviter(user.getId());

        if (inviter == null) {
            inviter = new Inviter(
                    user.getId(),
                    groupChatId,
                    user.getUserName(),
                    getUserName(user)
            );
        }

        return inviter;
    }
}
