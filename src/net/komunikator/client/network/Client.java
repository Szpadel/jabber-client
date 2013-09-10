package net.komunikator.client.network;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import de.root1.simon.SimonUnreferenced;
import de.root1.simon.annotation.SimonRemote;
import net.komunikator.client.Connections;
import net.komunikator.client.Contacts;
import net.komunikator.client.entities.Connection;
import net.komunikator.client.entities.Contact;
import net.komunikator.client.entities.Status;
import net.komunikator.shared.network.ClientCallbackInterface;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 06.09.13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
@SimonRemote(value = {ClientCallbackInterface.class})
public class Client implements ClientCallbackInterface, SimonUnreferenced {
    private Activity activity;

    public Client(Activity activity) {
        this.activity = activity;
    }

    private void prepareLooper() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    @Override
    public void toast(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("Client", "Server toast:" + message);
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void addContact(int id, int connectionId, String name, int status, String jid, String statusDescription) {
        Contacts contacts = Contacts.getInstance();
        Connections connections = Connections.getInstance();
        Connection connection = connections.getConnection(connectionId);
        Status statusEnum;
        switch (status) {
            case 0:
                statusEnum = Status.offline;
                break;
            case 1:
                statusEnum = Status.online;
                break;
            case 2:
                statusEnum = Status.away;
                break;
            default:
                statusEnum = Status.offline;
        }
        Contact contact = new Contact(id, name, statusEnum, jid, statusDescription, connection);
        contacts.addContact(contact);
    }

    @Override
    public void addConnection(int id, String username, String domain, String resource) {
        Connections connections = Connections.getInstance();
        Connection connection = new Connection(id, username, domain, resource);
        connections.addConnection(connection);
    }

    @Override
    public void unreferenced() {
        NetworkConnection.getInstance().disconnect();
        toast("Connection to server closed");
    }
}
