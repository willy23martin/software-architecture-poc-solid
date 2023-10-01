package org.architecture.solid.principles.poc.ocp.after.database;

import org.architecture.solid.principles.poc.ocp.model.Order;

import java.util.HashMap;
import java.util.Map;

public class DatabaseB implements IDatabase {

    private final Map<String, Object> ordersDatabase = new HashMap<>();

    @Override
    public void storeOrder(Order order) {
        ordersDatabase.put(order.getId(), order);
    }

    @Override
    public Object getOrdersDatabase() {
        return ordersDatabase;
    }
}
