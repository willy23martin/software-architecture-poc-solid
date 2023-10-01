package org.architecture.solid.principles.poc.dip.after.database;

import org.architecture.solid.principles.poc.dip.model.Order;

public interface IDatabasePersister {

    void storeOrder(Order order);

}
