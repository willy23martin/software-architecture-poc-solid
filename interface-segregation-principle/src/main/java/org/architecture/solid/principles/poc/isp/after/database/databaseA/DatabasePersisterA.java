package org.architecture.solid.principles.poc.isp.after.database.databaseA;

import org.architecture.solid.principles.poc.isp.after.database.IDatabasePersister;
import org.architecture.solid.principles.poc.isp.model.Order;

public class DatabasePersisterA implements IDatabasePersister {

    private DatabaseDataSourceA databaseDataSourceA;

    public DatabasePersisterA(final DatabaseDataSourceA databaseDataSourceA){
        this.databaseDataSourceA = databaseDataSourceA;
    }

    @Override
    public void storeOrder(Order order) {
        databaseDataSourceA.add(order);
    }
}
