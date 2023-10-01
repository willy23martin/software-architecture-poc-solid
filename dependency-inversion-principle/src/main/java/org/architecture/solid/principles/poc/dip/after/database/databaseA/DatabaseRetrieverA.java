package org.architecture.solid.principles.poc.dip.after.database.databaseA;

import org.architecture.solid.principles.poc.dip.after.database.IDatabaseRetriever;
import org.architecture.solid.principles.poc.dip.after.database.datasource.IDataSource;

public class DatabaseRetrieverA implements IDatabaseRetriever {

    private IDataSource databaseDataSourceA;

    public DatabaseRetrieverA(final IDataSource databaseDataSourceA) {
        this.databaseDataSourceA = databaseDataSourceA;
    }

    @Override
    public Object getOrdersDatabase() {
        return databaseDataSourceA;
    }
}
