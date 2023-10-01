package org.architecture.solid.principles.poc.dip.before.database;

import org.architecture.solid.principles.poc.dip.model.Order;

public interface IDatabasePersister {

    void storeOrder(Order order);

}
