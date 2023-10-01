package org.architecture.solid.principles.poc.dip.after.database.databaseA;

import org.architecture.solid.principles.poc.dip.after.database.datasource.IDataSource;
import org.architecture.solid.principles.poc.dip.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DatabaseDataSourceA implements IDataSource {

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
