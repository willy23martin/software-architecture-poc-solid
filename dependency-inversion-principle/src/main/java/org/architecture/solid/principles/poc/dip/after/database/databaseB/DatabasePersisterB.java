package org.architecture.solid.principles.poc.dip.after.database.databaseB;

import org.architecture.solid.principles.poc.dip.after.database.IDatabasePersister;
import org.architecture.solid.principles.poc.dip.after.database.datasource.IDataSource;
import org.architecture.solid.principles.poc.dip.model.Order;

public class DatabasePersisterB implements IDatabasePersister {

    private IDataSource databaseDataSourceB;

    public DatabasePersisterB(final IDataSource databaseDataSourceB){
        this.databaseDataSourceB = databaseDataSourceB;
    }

    @Override
    public void storeOrder(Order order) {
        databaseDataSourceB.add(order);
    }
}
