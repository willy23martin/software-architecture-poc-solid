package org.architecture.solid.principles.poc.dip.before.database.databaseA;

import org.architecture.solid.principles.poc.dip.before.database.IDatabaseRetriever;

public class DatabaseRetrieverA implements IDatabaseRetriever {

    private DatabaseDataSourceA databaseDataSourceA;

    public DatabaseRetrieverA(final DatabaseDataSourceA databaseDataSourceA) {
        this.databaseDataSourceA = databaseDataSourceA;
    }

    @Override
    public Object getOrdersDatabase() {
        return databaseDataSourceA;
    }
}
