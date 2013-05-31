package net.komunikator.client.entities;

import net.komunikator.client.Conversations;
import net.komunikator.client.R;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.05.13
 * Time: 02:01
 * To change this template use File | Settings | File Templates.
 */
public class Contact {
    int id;
    protected String name;
    Status status;
    String jid;
    String statusDescription;
    Conversation conversation;

    public Contact(int id, String name, Status status, String jid, String statusDescription) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.jid = jid;
        this.statusDescription = statusDescription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Conversation getConversation() {
        if(conversation == null) {
            Conversations conversations = Conversations.getInstance();
            conversation = conversations.getConversation(this);
        }
        return conversation;
    }

    public int getStatusResource() {
        switch (status) {
            case online:
                return R.drawable.online;
            case away:
                return R.drawable.away;
            case offline:
            default:
                return R.drawable.offline;
        }
    }
}
