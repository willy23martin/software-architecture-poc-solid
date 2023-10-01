package org.architecture.solid.principles.poc.dip.before.database.databaseB;

import org.architecture.solid.principles.poc.dip.before.database.IDatabaseRetriever;

public class DatabaseRetrieverB implements IDatabaseRetriever {

    private DatabaseDataSourceB databaseDataSourceB;

    public DatabaseRetrieverB(final DatabaseDataSourceB databaseDataSourceB) {
        this.databaseDataSourceB = databaseDataSourceB;
    }

    @Override
    public Object getOrdersDatabase() {
        return databaseDataSourceB;
    }
}
