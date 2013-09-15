package net.komunikator.client;

import net.komunikator.client.entities.Connection;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
public class Connections extends Observable {
    private static Connections ourInstance = new Connections();

    Map<Integer, Connection> connections;

    public static Connections getInstance() {
        return ourInstance;
    }

    private Connections() {
        connections = new LinkedHashMap<Integer, Connection>();
    }

    public List<Connection> getConections() {
        return new LinkedList<Connection>(connections.values());
    }

    public Connection getConnection(int id) {
        return connections.get(id);
    }

    public void addConnection(Connection connection) {
        connections.put(connection.getId(), connection);
        notifyObservers();
    }


}
