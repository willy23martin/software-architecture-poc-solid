package org.architecture.solid.principles.poc.isp.after.database;

import org.architecture.solid.principles.poc.isp.model.Order;

public interface IDatabasePersister {

    void storeOrder(Order order);

}
