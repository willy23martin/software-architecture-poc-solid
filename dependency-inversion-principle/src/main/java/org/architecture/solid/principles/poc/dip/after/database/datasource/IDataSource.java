package org.architecture.solid.principles.poc.dip.after.database.datasource;

import org.architecture.solid.principles.poc.dip.model.Order;

public interface IDataSource {

    Object getOrdersDatabase();
    void add(Order order);

}
