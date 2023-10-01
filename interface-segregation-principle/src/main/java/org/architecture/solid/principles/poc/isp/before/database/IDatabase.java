package org.architecture.solid.principles.poc.isp.before.database;

import org.architecture.solid.principles.poc.isp.model.Order;

public interface IDatabase {

    // Uso#1: por parte del Order Persister.
    // Posible Uso#2 NO DESEADO: Por parte del main al momento de imprimir el contenido de la base de datos,
    // POR ELLO ESTA INTERFAZ DEBE SEGREGARSE!
    void storeOrder(Order order);

    // Uso#1: por el OrderPersister.
    Object getOrdersDatabase();

}
