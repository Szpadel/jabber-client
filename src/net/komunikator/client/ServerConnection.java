package net.komunikator.client;

import net.komunikator.client.entities.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.05.13
 * Time: 01:55
 * To change this template use File | Settings | File Templates.
 */
public class ServerConnection {
    private static ServerConnection ourInstance = new ServerConnection();

    protected String serverAddress;
    protected String password;

    public static ServerConnection getInstance() {
        return ourInstance;
    }

    private ServerConnection() {
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void connect() {
        Connections connections = Connections.getInstance();
        connections.addConnection(new Connection(0, "dupa", "cyce", "gowno.org", "android"));
    }
}
