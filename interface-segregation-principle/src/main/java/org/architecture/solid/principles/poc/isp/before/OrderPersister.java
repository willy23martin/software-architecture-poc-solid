package org.architecture.solid.principles.poc.isp.before;

import org.architecture.solid.principles.poc.isp.before.database.IDatabase;
import org.architecture.solid.principles.poc.isp.model.Order;

import java.util.List;

public class OrderPersister {

    private IDatabase database;

    public OrderPersister(IDatabase database) {
        this.database = database;
    }

    // Actor#3: Database Administrator: is the responsible to persists the orders and their information in the database.
    public void persistOrders(List<Order> orders) {
        orders.forEach(order -> database.storeOrder(order));
    }

    public IDatabase getDatabase() {
        return database;
    }
}
