package org.architecture.solid.principles.poc.lsp.after.database;

import org.architecture.solid.principles.poc.lsp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DatabaseA implements IDatabase {

    private final List<Order> ordersDatabase = new ArrayList<>();

    @Override
    public void storeOrder(Order order) {
        ordersDatabase.add(order);
    }

    @Override
    public Object getOrdersDatabase() {
        return ordersDatabase;
    }
}
