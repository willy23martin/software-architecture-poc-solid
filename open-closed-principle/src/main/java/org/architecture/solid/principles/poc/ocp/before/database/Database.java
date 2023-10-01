package org.architecture.solid.principles.poc.ocp.before.database;

import org.architecture.solid.principles.poc.ocp.model.Order;

import java.util.HashMap;

public class Database {
    private HashMap<String, Order> ordersDatabase = new HashMap<>();

    public HashMap<String, Order> getOrdersDatabase() {
        return ordersDatabase;
    }
}
