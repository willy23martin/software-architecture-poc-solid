package org.architecture.solid.principles.poc.ocp;

import org.architecture.solid.principles.poc.ocp.after.database.DatabaseA;
import org.architecture.solid.principles.poc.ocp.after.database.DatabaseB;
import org.architecture.solid.principles.poc.ocp.before.OrderManagerFacade;
import org.architecture.solid.principles.poc.ocp.model.Order;

import java.util.List;

public class OpenClosedPrinciple {
    public static void main(String[] args) {
        System.out.println("Open-Closed Principle! ==== ");

        List<Order> orders = List.of(
                new Order("1", 100.98),
                new Order("2", 200.12)
        );

        OrderManagerFacade orderManagerFacade = new OrderManagerFacade();
        orders.forEach(order -> orderManagerFacade.placeOrder(order));
        orderManagerFacade.persistOrders();
        System.out.println("Before: total payment: " + orderManagerFacade.calculateTotalPayment());
        System.out.println("Before: database: " + orderManagerFacade.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        org.architecture.solid.principles.poc.ocp.after.OrderManagerFacade orderManagerFacadeWithDatabaseA = new org.architecture.solid.principles.poc.ocp.after.OrderManagerFacade(new DatabaseA());
        orders.forEach(order -> orderManagerFacadeWithDatabaseA.placeOrder(order));
        orderManagerFacadeWithDatabaseA.persistOrders();
        System.out.println("After with DatabaseA: total payment: " + orderManagerFacadeWithDatabaseA.calculateTotalPayment());
        System.out.println("After with DatabaseA: database: " + orderManagerFacadeWithDatabaseA.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        org.architecture.solid.principles.poc.ocp.after.OrderManagerFacade orderManagerFacadeWithDatabaseB = new org.architecture.solid.principles.poc.ocp.after.OrderManagerFacade(new DatabaseB());
        orders.forEach(order -> orderManagerFacadeWithDatabaseB.placeOrder(order));
        orderManagerFacadeWithDatabaseB.persistOrders();
        System.out.println("After with DatabaseB: total payment: " + orderManagerFacadeWithDatabaseB.calculateTotalPayment());
        System.out.println("After with DatabaseB: database: " + orderManagerFacadeWithDatabaseB.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("========");

        System.out.println("End ======");
    }
}