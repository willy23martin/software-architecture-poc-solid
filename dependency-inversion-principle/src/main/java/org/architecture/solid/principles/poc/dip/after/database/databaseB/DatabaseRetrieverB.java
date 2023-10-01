package org.architecture.solid.principles.poc.dip.after.database.databaseB;

import org.architecture.solid.principles.poc.dip.after.database.IDatabaseRetriever;
import org.architecture.solid.principles.poc.dip.after.database.datasource.IDataSource;

public class DatabaseRetrieverB implements IDatabaseRetriever {

    private IDataSource databaseDataSourceB;

    public DatabaseRetrieverB(final IDataSource databaseDataSourceB) {
        this.databaseDataSourceB = databaseDataSourceB;
    }

    @Override
    public Object getOrdersDatabase() {
        return databaseDataSourceB;
    }
}
