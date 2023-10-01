package org.architecture.solid.principles.poc.ocp.after.database;

import org.architecture.solid.principles.poc.ocp.model.Order;

public interface IDatabase {

    void storeOrder(Order order);

    Object getOrdersDatabase();

}
