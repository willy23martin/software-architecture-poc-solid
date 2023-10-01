package org.architecture.solid.principles.poc.lsp;

import org.architecture.solid.principles.poc.lsp.after.payment.OrdinaryPaymentCalculator;
import org.architecture.solid.principles.poc.lsp.before.OrderManagerFacade;
import org.architecture.solid.principles.poc.lsp.before.database.DatabaseA;
import org.architecture.solid.principles.poc.lsp.before.database.DatabaseB;
import org.architecture.solid.principles.poc.lsp.before.payment.DiscountPointsPaymentCalculator;
import org.architecture.solid.principles.poc.lsp.before.payment.PaymentCalculator;
import org.architecture.solid.principles.poc.lsp.model.Order;

import java.util.List;

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        System.out.println("Liskov Substitution Principle! ==== ");

        List<Order> orders = List.of(
                new Order("1", 100.98),
                new Order("2", 200.12)
        );

        OrderManagerFacade orderManagerFacadeWithDatabaseA = new OrderManagerFacade(new DatabaseA(), new PaymentCalculator());
        orders.forEach(order -> orderManagerFacadeWithDatabaseA.placeOrder(order));
        orderManagerFacadeWithDatabaseA.persistOrders();
        System.out.println("Before with DatabaseA and PaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseA.calculateTotalPayment());
        System.out.println("Before with DatabaseA and PaymentCalculator: database: " + orderManagerFacadeWithDatabaseA.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        OrderManagerFacade orderManagerFacadeWithDatabaseB = new OrderManagerFacade(new DatabaseB(), new DiscountPointsPaymentCalculator());
        orders.forEach(order -> orderManagerFacadeWithDatabaseB.placeOrder(order));
        orderManagerFacadeWithDatabaseB.persistOrders();
        System.out.println("Before with DatabaseB and DiscountPointsPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseB.calculateTotalPayment());
        System.out.println("Before with DatabaseB and DiscountPointsPaymentCalculator: database: " + orderManagerFacadeWithDatabaseB.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        org.architecture.solid.principles.poc.lsp.after.payment.PaymentCalculator ordinaryPaymentCalculator = new OrdinaryPaymentCalculator();
        org.architecture.solid.principles.poc.lsp.after.OrderManagerFacade orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator = new org.architecture.solid.principles.poc.lsp.after.OrderManagerFacade(new org.architecture.solid.principles.poc.lsp.after.database.DatabaseA(), ordinaryPaymentCalculator);
        orders.forEach(order -> orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.placeOrder(order));
        orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.persistOrders();
        System.out.println("After with DatabaseA and OrdinaryPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.calculateTotalPayment());
        System.out.println("After with DatabaseA and OrdinaryPaymentCalculator: database: " + orderManagerFacadeWithDatabaseAAndOrdinaryPaymentCalculator.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        org.architecture.solid.principles.poc.lsp.after.payment.PaymentCalculator discountPointsPaymentCalculator = new org.architecture.solid.principles.poc.lsp.after.payment.DiscountPointsPaymentCalculator();
        org.architecture.solid.principles.poc.lsp.after.OrderManagerFacade orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator = new org.architecture.solid.principles.poc.lsp.after.OrderManagerFacade(new org.architecture.solid.principles.poc.lsp.after.database.DatabaseA(), discountPointsPaymentCalculator);
        orders.forEach(order -> orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.placeOrder(order));
        orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.persistOrders();
        System.out.println("After with DatabaseA and DiscountPointsPaymentCalculator: total payment: " + orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.calculateTotalPayment());
        System.out.println("After with DatabaseA and DiscountPointsPaymentCalculator: database: " + orderManagerFacadeWithDatabaseAAndDiscountPointsPaymentCalculator.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        System.out.println("End ======");
    }
}