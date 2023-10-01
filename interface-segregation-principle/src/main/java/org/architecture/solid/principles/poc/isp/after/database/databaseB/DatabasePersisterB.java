package org.architecture.solid.principles.poc.isp.after.database.databaseB;

import org.architecture.solid.principles.poc.isp.after.database.IDatabasePersister;
import org.architecture.solid.principles.poc.isp.model.Order;

public class DatabasePersisterB implements IDatabasePersister {

    private DatabaseDataSourceB databaseDataSourceB;

    public DatabasePersisterB(final DatabaseDataSourceB databaseDataSourceB){
        this.databaseDataSourceB = databaseDataSourceB;
    }

    @Override
    public void storeOrder(Order order) {
        databaseDataSourceB.add(order);
    }
}
