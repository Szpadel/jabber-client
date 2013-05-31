package net.komunikator.client;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.05.13
 * Time: 01:55
 * To change this template use File | Settings | File Templates.
 */
public class Connection {
    private static Connection ourInstance = new Connection();

    protected String serverAddress;
    protected String password;

    public static Connection getInstance() {
        return ourInstance;
    }

    private Connection() {
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
}
