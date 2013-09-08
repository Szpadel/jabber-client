package net.komunikator.client.network;

import android.app.Activity;
import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import net.komunikator.shared.network.ServerInterface;
import net.komunikator.shared.network.SessionInterface;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 06.09.13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class NetworkConnection {
    private static NetworkConnection ourInstance = new NetworkConnection();

    public static NetworkConnection getInstance() {
        return ourInstance;
    }

    private int port = 13337;
    private Activity activity;
    private ServerInterface server;
    private Client client;
    private SessionInterface session;
    Lookup lookup;

    private NetworkConnection() {
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void checkInit() {
        if (activity == null) {
            throw new IllegalStateException("activity is not set");
        }
    }

    public boolean connect(String address) {
        try {
            lookup = Simon.createNameLookup(address, port);
            server = (ServerInterface) lookup.lookup("server");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return server != null;
    }

    public boolean login(String name, String password) {
        checkInit();

        if (session != null) {
            throw new IllegalStateException("Already logged in");
        }
        if (client == null) {
            client = new Client(activity);
        }
        if (server == null) {
            throw new IllegalStateException("Not connected to server");
        }

        session = server.login(name, password, client);
        return session != null;
    }

    public void disconnect() {
        if (session != null) {
            session = null;
        }
        if (client != null) {
            client = null;
        }
        lookup.release(server);
        server = null;
        lookup = null;
    }

    public boolean isLoggedIn() {
        return session != null && session.isValid();
    }

    public ServerInterface getServer() {
        return server;
    }

    public Client getClient() {
        return client;
    }

    public SessionInterface getSession() {
        return session;
    }
}
