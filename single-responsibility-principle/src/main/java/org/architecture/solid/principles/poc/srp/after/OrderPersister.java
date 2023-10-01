package org.architecture.solid.principles.poc.srp.after;

import org.architecture.solid.principles.poc.srp.model.Database;
import org.architecture.solid.principles.poc.srp.model.Order;

import java.util.List;

public class OrderPersister {

    private final Database database = new Database();

    // Actor#3: Database Administrator: is the responsible to persists the orders and their information in the database.
    public void persistOrders(List<Order> orders) {
        orders.forEach(order -> database.getOrdersDatabase().put(order.getId(), order));
    }

    public Database getDatabase() {
        return database;
    }
}
