package org.architecture.solid.principles.poc.isp;

import org.architecture.solid.principles.poc.isp.after.database.IDatabasePersister;
import org.architecture.solid.principles.poc.isp.after.database.IDatabaseRetriever;
import org.architecture.solid.principles.poc.isp.after.database.databaseA.DatabaseDataSourceA;
import org.architecture.solid.principles.poc.isp.after.database.databaseA.DatabasePersisterA;
import org.architecture.solid.principles.poc.isp.after.database.databaseA.DatabaseRetrieverA;
import org.architecture.solid.principles.poc.isp.after.database.databaseB.DatabaseDataSourceB;
import org.architecture.solid.principles.poc.isp.after.database.databaseB.DatabasePersisterB;
import org.architecture.solid.principles.poc.isp.after.database.databaseB.DatabaseRetrieverB;
import org.architecture.solid.principles.poc.isp.before.OrderManagerFacade;
import org.architecture.solid.principles.poc.isp.before.database.DatabaseA;
import org.architecture.solid.principles.poc.isp.before.database.DatabaseB;
import org.architecture.solid.principles.poc.isp.before.payment.DiscountPointsPaymentCalculator;
import org.architecture.solid.principles.poc.isp.before.payment.OrdinaryPaymentCalculator;
import org.architecture.solid.principles.poc.isp.before.payment.PaymentCalculator;
import org.architecture.solid.principles.poc.isp.model.Order;

import java.util.List;

public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        System.out.println("Interface Segregation Principle! ==== ");

        List<Order> orders = List.of(
                new Order("1", 100.98),
                new Order("2", 200.12)
        );

        PaymentCalculator ordinaryPaymentCalculator = new OrdinaryPaymentCalculator();
        OrderManagerFacade orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator = new OrderManagerFacade(new DatabaseA(), ordinaryPaymentCalculator);
        orders.forEach(order -> orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.placeOrder(order));
        orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.persistOrders();
        System.out.println("Before with DatabaseA and OrdinaryPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.calculateTotalPayment());
        System.out.println("Before with DatabaseA and OrdinaryPaymentCalculator: database: " + orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        PaymentCalculator discountPointsPaymentCalculator = new DiscountPointsPaymentCalculator();
        OrderManagerFacade orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator = new OrderManagerFacade(new DatabaseB(), discountPointsPaymentCalculator);
        orders.forEach(order -> orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.placeOrder(order));
        orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.persistOrders();
        System.out.println("Before with DatabaseB and DiscountPointsPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.calculateTotalPayment());
        System.out.println("Before with DatabaseB and DiscountPointsPaymentCalculator: database: " + orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        // Esta clase usará el IDatabasePersister A para inicializar el OrderManagerFacade
        // PERO usará el IDatabaseRetriever A, que no debe ser usado por el OrderManagerFacade, para obtener los datos que actualmente están en la base de datos.
        DatabaseDataSourceA databaseDataSourceA = new DatabaseDataSourceA();
        IDatabasePersister databasePersisterA = new DatabasePersisterA(databaseDataSourceA);
        IDatabaseRetriever databaseRetrieverA = new DatabaseRetrieverA(databaseDataSourceA);
        org.architecture.solid.principles.poc.isp.after.payment.PaymentCalculator ordinaryPaymentCalculatorSegregated = new org.architecture.solid.principles.poc.isp.after.payment.OrdinaryPaymentCalculator();
        org.architecture.solid.principles.poc.isp.after.OrderManagerFacade orderManagerFacadeSegregated = new org.architecture.solid.principles.poc.isp.after.OrderManagerFacade(databasePersisterA, ordinaryPaymentCalculatorSegregated);
        orders.forEach(order -> orderManagerFacadeSegregated.placeOrder(order));
        orderManagerFacadeSegregated.persistOrders();
        System.out.println("After with DatabaseA and OrdinaryPaymentCalculator: total payment: " + orderManagerFacadeSegregated.calculateTotalPayment());
        System.out.println("After with DatabaseA and OrdinaryPaymentCalculator: database: " + databaseRetrieverA.getOrdersDatabase().toString());
        System.out.println("========");

        // Esta clase usará el IDatabasePersister B para inicializar el OrderManagerFacade
        // PERO usará el IDatabaseRetriever B, que no debe ser usado por el OrderManagerFacade, para obtener los datos que actualmente están en la base de datos.
        DatabaseDataSourceB databaseDataSourceB = new DatabaseDataSourceB();
        IDatabasePersister databasePersisterB = new DatabasePersisterB(databaseDataSourceB);
        IDatabaseRetriever databaseRetrieverB = new DatabaseRetrieverB(databaseDataSourceB);
        org.architecture.solid.principles.poc.isp.after.payment.PaymentCalculator discountPointsPaymentCalculatorSegregated = new org.architecture.solid.principles.poc.isp.after.payment.DiscountPointsPaymentCalculator();
        org.architecture.solid.principles.poc.isp.after.OrderManagerFacade orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated = new org.architecture.solid.principles.poc.isp.after.OrderManagerFacade(databasePersisterB, discountPointsPaymentCalculatorSegregated);
        orders.forEach(order -> orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated.placeOrder(order));
        orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated.persistOrders();
        System.out.println("After with DatabaseB and DiscountPointsPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseBAndDiscountPointsPaymentCalculatorSegregated.calculateTotalPayment());
        System.out.println("After with DatabaseB and DiscountPointsPaymentCalculator: database: " + databaseRetrieverB.getOrdersDatabase().toString());
        System.out.println("========");

        System.out.println("End ======");
    }
}