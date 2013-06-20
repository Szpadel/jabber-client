package net.komunikator.client.entities;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class Connection {
    int id;
    String username;
    String password;
    String domain;
    String resource;

    public Connection(int id, String username, String password, String domain, String resource) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
