package org.architecture.solid.principles.poc.dip;

import org.architecture.solid.principles.poc.dip.after.database.datasource.IDataSource;
import org.architecture.solid.principles.poc.dip.before.OrderManagerFacade;
import org.architecture.solid.principles.poc.dip.before.database.IDatabasePersister;
import org.architecture.solid.principles.poc.dip.before.database.IDatabaseRetriever;
import org.architecture.solid.principles.poc.dip.before.database.databaseA.DatabaseDataSourceA;
import org.architecture.solid.principles.poc.dip.before.database.databaseA.DatabasePersisterA;
import org.architecture.solid.principles.poc.dip.before.database.databaseA.DatabaseRetrieverA;
import org.architecture.solid.principles.poc.dip.before.database.databaseB.DatabaseDataSourceB;
import org.architecture.solid.principles.poc.dip.before.database.databaseB.DatabasePersisterB;
import org.architecture.solid.principles.poc.dip.before.database.databaseB.DatabaseRetrieverB;
import org.architecture.solid.principles.poc.dip.before.payment.DiscountPointsPaymentCalculator;
import org.architecture.solid.principles.poc.dip.before.payment.OrdinaryPaymentCalculator;
import org.architecture.solid.principles.poc.dip.before.payment.PaymentCalculator;
import org.architecture.solid.principles.poc.dip.model.Order;

import java.util.List;

public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        System.out.println("Dependency Inversion Principle! ==== ");

        List<Order> orders = List.of(
                new Order("1", 100.98),
                new Order("2", 200.12)
        );

        // Esta clase usará el IDatabasePersister A para inicializar el OrderManagerFacade
        // PERO usará el IDatabaseRetriever A, que no debe ser usado por el OrderManagerFacade, para obtener los datos que actualmente están en la base de datos.
        DatabaseDataSourceA databaseDataSourceA = new DatabaseDataSourceA();
        IDatabasePersister databasePersisterA = new DatabasePersisterA(databaseDataSourceA);
        IDatabaseRetriever databaseRetrieverA = new DatabaseRetrieverA(databaseDataSourceA);
        PaymentCalculator ordinaryPaymentCalculatorSegregated = new OrdinaryPaymentCalculator();
        OrderManagerFacade orderManagerFacadeSegregated = new OrderManagerFacade(databasePersisterA, ordinaryPaymentCalculatorSegregated);
        orders.forEach(order -> orderManagerFacadeSegregated.placeOrder(order));
        orderManagerFacadeSegregated.persistOrders();
        System.out.println("Before with DatabaseA and OrdinaryPaymentCalculator: total payment: " + orderManagerFacadeSegregated.calculateTotalPayment());
        System.out.println("Before with DatabaseA and OrdinaryPaymentCalculator: database: " + databaseRetrieverA.getOrdersDatabase().toString());
        System.out.println("========");

        // Esta clase usará el IDatabasePersister B para inicializar el OrderManagerFacade
        // PERO usará el IDatabaseRetriever B, que no debe ser usado por el OrderManagerFacade, para obtener los datos que actualmente están en la base de datos.
        DatabaseDataSourceB databaseDataSourceB = new DatabaseDataSourceB();
        IDatabasePersister databasePersisterB = new DatabasePersisterB(databaseDataSourceB);
        IDatabaseRetriever databaseRetrieverB = new DatabaseRetrieverB(databaseDataSourceB);
        PaymentCalculator discountPointsPaymentCalculatorSegregated = new DiscountPointsPaymentCalculator();
        OrderManagerFacade orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated = new OrderManagerFacade(databasePersisterB, discountPointsPaymentCalculatorSegregated);
        orders.forEach(order -> orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated.placeOrder(order));
        orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated.persistOrders();
        System.out.println("Before with DatabaseB and DiscountPointsPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated.calculateTotalPayment());
        System.out.println("Before with DatabaseB and DiscountPointsPaymentCalculator: database: " + databaseRetrieverB.getOrdersDatabase().toString());
        System.out.println("========");

        // PROBLEMA: Tanto el DataPersister como el DataRetriever dependen de una implementación concreta de un DataSource
        // y de los servicios que pueda ofrecer, con sus parametrizaciones particulares: una List o un Map (dos estructuras de datos diferentes).
        // Por ende, ambos DEBERÍAN DEPENDER SEA DE UNA INTERFAZ O DE UNA ABSTRACCIÓN EN VEZ DE UNA IMPLEMENTACIÓN EN CONCRETO.

        // Ahora se va a usar la interfaz IDataSource para pasarlo como dependencia abstracta del Persister y del Retriever en cada caso:
        IDataSource databaseDataSourceAInjected = new org.architecture.solid.principles.poc.dip.after.database.databaseA.DatabaseDataSourceA();
        org.architecture.solid.principles.poc.dip.after.database.IDatabasePersister databasePersisterAInjected = new org.architecture.solid.principles.poc.dip.after.database.databaseA.DatabasePersisterA(databaseDataSourceAInjected);
        org.architecture.solid.principles.poc.dip.after.database.IDatabaseRetriever databaseRetrieverAInjected = new org.architecture.solid.principles.poc.dip.after.database.databaseA.DatabaseRetrieverA(databaseDataSourceAInjected);
        org.architecture.solid.principles.poc.dip.after.payment.PaymentCalculator ordinaryPaymentCalculatorSegregatedInjected = new org.architecture.solid.principles.poc.dip.after.payment.OrdinaryPaymentCalculator();
        org.architecture.solid.principles.poc.dip.after.OrderManagerFacade orderManagerFacadeSegregatedInjected = new org.architecture.solid.principles.poc.dip.after.OrderManagerFacade(databasePersisterAInjected, ordinaryPaymentCalculatorSegregatedInjected);
        orders.forEach(order -> orderManagerFacadeSegregatedInjected.placeOrder(order));
        orderManagerFacadeSegregatedInjected.persistOrders();
        System.out.println("After with DatabaseA and OrdinaryPaymentCalculator: total payment: " + orderManagerFacadeSegregatedInjected.calculateTotalPayment());
        System.out.println("After with DatabaseA and OrdinaryPaymentCalculator: database: " + databaseRetrieverAInjected.getOrdersDatabase().toString());
        System.out.println("========");

        IDataSource databaseDataSourceBInjected = new org.architecture.solid.principles.poc.dip.after.database.databaseB.DatabaseDataSourceB();
        org.architecture.solid.principles.poc.dip.after.database.IDatabasePersister databasePersisterBInjected = new org.architecture.solid.principles.poc.dip.after.database.databaseB.DatabasePersisterB(databaseDataSourceBInjected);
        org.architecture.solid.principles.poc.dip.after.database.IDatabaseRetriever databaseRetrieverBInjected = new org.architecture.solid.principles.poc.dip.after.database.databaseA.DatabaseRetrieverA(databaseDataSourceBInjected);
        org.architecture.solid.principles.poc.dip.after.payment.PaymentCalculator discountPointsPaymentCalculatorSegregatedIbjected = new org.architecture.solid.principles.poc.dip.after.payment.DiscountPointsPaymentCalculator();
        org.architecture.solid.principles.poc.dip.after.OrderManagerFacade orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregatedInjected = new org.architecture.solid.principles.poc.dip.after.OrderManagerFacade(databasePersisterBInjected, discountPointsPaymentCalculatorSegregatedIbjected);
        orders.forEach(order -> orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregatedInjected.placeOrder(order));
        orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregatedInjected.persistOrders();
        System.out.println("After with DatabaseB and DiscountPointsPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregatedInjected.calculateTotalPayment());
        System.out.println("After with DatabaseB and DiscountPointsPaymentCalculator: database: " + databaseRetrieverBInjected.getOrdersDatabase().toString());
        System.out.println("========");


        System.out.println("End ======");
    }
}