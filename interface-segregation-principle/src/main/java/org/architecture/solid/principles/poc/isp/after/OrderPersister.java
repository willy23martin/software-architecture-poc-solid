package org.architecture.solid.principles.poc.isp.after;

import org.architecture.solid.principles.poc.isp.after.database.IDatabasePersister;
import org.architecture.solid.principles.poc.isp.model.Order;

import java.util.List;

public class OrderPersister {

    private IDatabasePersister databasePersister;

    public OrderPersister(IDatabasePersister database) {
        this.databasePersister = database;
    }

    // Actor#3: Database Administrator: is the responsible to persists the orders and their information in the database.
    public void persistOrders(List<Order> orders) {
        orders.forEach(order -> databasePersister.storeOrder(order));
    }

    public IDatabasePersister getDatabasePersister() {
        return databasePersister;
    }
}
