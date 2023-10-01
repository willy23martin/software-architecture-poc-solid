package org.architecture.solid.principles.poc.isp.after.database.databaseA;

import org.architecture.solid.principles.poc.isp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DatabaseDataSourceA {

    private final List<Order> ordersDatabase = new ArrayList<>();

    public List<Order> getOrdersDatabase() {
        return ordersDatabase;
    }

    public void add(Order order) {
        ordersDatabase.add(order);
    }

    @Override
    public String toString(){
        return ordersDatabase.toString();
    }
}
