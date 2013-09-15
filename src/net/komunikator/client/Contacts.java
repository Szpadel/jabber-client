package net.komunikator.client;

import net.komunikator.client.entities.Connection;
import net.komunikator.client.entities.Contact;
import net.komunikator.client.entities.Status;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.05.13
 * Time: 02:01
 * To change this template use File | Settings | File Templates.
 */
public class Contacts extends Observable {
    private static Contacts ourInstance = new Contacts();

    Map<Integer, Contact> contacts;
    List<Contact> contactsList;

    public static Contacts getInstance() {
        return ourInstance;
    }

    private Contacts() {
        contacts = new LinkedHashMap<Integer, Contact>();
        contactsList = new LinkedList<Contact>();
    }

    public void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
        contactsList.add(contact);
        setChanged();
        notifyObservers();
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contactsList.add(contact);
        setChanged();
        notifyObservers();
    }

    public List<Contact> getContacts() {
        return contactsList;
        //return new LinkedList<Contact>(contacts.values());
    }

    public Contact getContact(int id) {
        return contacts.get(id);
    }

    public void loadFixtures() {
        Connections connections = Connections.getInstance();
        Connection connection = connections.getConnection(0);

        contacts.put(0, new Contact(0, "John Smith", Status.online, "john@example.org", "Have a nice day!", connection));
        contacts.put(1, new Contact(1, "Anna Smith", Status.away, "john@example.org", "", connection));
        contacts.put(2, new Contact(2, "Bura Michal", Status.offline, "john@example.org",
                "\"Kurczak Kurczak Kurczak Kurczak\" -- Student", connection));
        contacts.put(3, new Contact(3, "Michael", Status.online, "john@example.org", "I like trains...", connection));
    }
}
