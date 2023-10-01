package org.architecture.solid.principles.poc.dip.after.database.databaseA;

import org.architecture.solid.principles.poc.dip.after.database.IDatabasePersister;
import org.architecture.solid.principles.poc.dip.after.database.datasource.IDataSource;
import org.architecture.solid.principles.poc.dip.model.Order;

public class DatabasePersisterA implements IDatabasePersister {

    private IDataSource databaseDataSourceA;

    public DatabasePersisterA(final IDataSource databaseDataSourceA){
        this.databaseDataSourceA = databaseDataSourceA;
    }

    @Override
    public void storeOrder(Order order) {
        databaseDataSourceA.add(order);
    }
}
