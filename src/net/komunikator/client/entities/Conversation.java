package net.komunikator.client.entities;

import android.os.AsyncTask;
import net.komunikator.client.network.NetworkConnection;
import net.komunikator.shared.network.SessionInterface;

import java.util.List;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 02:25
 * To change this template use File | Settings | File Templates.
 */
public class Conversation extends Observable {
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
        setChanged();
        notifyObservers();
    }

    public void sendMessage(final String msg) {
        AsyncTask<Void, Void, Void> sendMsg = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SessionInterface session = NetworkConnection.getInstance().getSession();
                session.sendMessage(contact.getId(), msg);
                return null;
            }
        };

        sendMsg.execute();
    }
}
