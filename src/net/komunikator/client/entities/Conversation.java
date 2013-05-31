package net.komunikator.client.entities;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 02:25
 * To change this template use File | Settings | File Templates.
 */
public class Conversation {
    protected Contact contact;
    protected List<Message> messages;

    public Conversation(Contact contact, List<Message> messages) {
        this.contact = contact;
        this.messages = messages;
    }

    public Contact getContact() {
        return contact;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void sendMessage(String msg) {
        Message message = new Message(0, null, msg, new Date()); // FIXME: wyslij do serwera
        messages.add(message);
    }
}
