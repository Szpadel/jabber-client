package net.komunikator.client;

import net.komunikator.client.entities.Contact;
import net.komunikator.client.entities.Conversation;
import net.komunikator.client.entities.Message;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 03:43
 * To change this template use File | Settings | File Templates.
 */
public class Conversations extends Observable {
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
        if (conv == null) {
            conv = new Conversation(contact, new LinkedList<Message>());
            conversationMap.put(contact, conv);
        }
        return conv;
    }
}
