package org.architecture.solid.principles.poc.lsp.before.database;

import org.architecture.solid.principles.poc.lsp.model.Order;

public interface IDatabase {

    void storeOrder(Order order);

    Object getOrdersDatabase();

}
