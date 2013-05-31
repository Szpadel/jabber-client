package net.komunikator.client;

import net.komunikator.client.entities.Contact;
import net.komunikator.client.entities.Conversation;
import net.komunikator.client.entities.Message;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 03:43
 * To change this template use File | Settings | File Templates.
 */
public class Conversations {
    private static Conversations ourInstance = new Conversations();
    Map<Contact, Conversation> conversationMap;

    public static Conversations getInstance() {
        return ourInstance;
    }

    private Conversations() {
        conversationMap = new LinkedHashMap<Contact, Conversation>();
    }

    public void addConversation(Conversation conversation) {
        conversationMap.put(conversation.getContact(), conversation);
    }

    public Conversation getConversation(Contact contact) {
        Conversation conv = conversationMap.get(contact);
        if(conv == null) {
            // FIXME: get from server
            conv = new Conversation(contact, new LinkedList<Message>());
            conversationMap.put(contact, conv);

            if(contact.getId() == 0) {
                conv.addMessage(new Message(0, contact, "Hello Mr Szpadel :)", new Date()));
                conv.addMessage(new Message(0, contact, "Have a nice day :)", new Date()));
            }
        }
        return conv;
    }
}
