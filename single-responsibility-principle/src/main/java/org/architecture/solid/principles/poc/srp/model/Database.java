package org.architecture.solid.principles.poc.srp.model;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, Order> ordersDatabase = new HashMap<>();

    public Map<String, Order> getOrdersDatabase() {
        return ordersDatabase;
    }
}
