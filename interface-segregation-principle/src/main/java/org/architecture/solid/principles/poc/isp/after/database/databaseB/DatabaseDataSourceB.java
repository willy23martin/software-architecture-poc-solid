package org.architecture.solid.principles.poc.isp.after.database.databaseB;

import org.architecture.solid.principles.poc.isp.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseDataSourceB {

    private final Map<String, Object> ordersDatabase = new HashMap<>();

    public Map<String, Object> getOrdersDatabase() {
        return ordersDatabase;
    }

    public void add(Order order) {
        ordersDatabase.put(order.getId(), order);
    }

    @Override
    public String toString(){
        return ordersDatabase.toString();
    }
}
