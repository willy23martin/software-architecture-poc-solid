package org.architecture.solid.principles.poc;

import org.architecture.solid.principles.poc.srp.after.OrderManagerFacade;
import org.architecture.solid.principles.poc.srp.before.OrderManager;
import org.architecture.solid.principles.poc.srp.model.Order;

import java.util.List;

public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        System.out.println("Single Responsibility Principle! ==== ");

        List<Order> orders = List.of(
                new Order("1", 100.98),
                new Order("2", 200.12)
        );

        OrderManager orderManager = new OrderManager();
        orders.forEach(order -> orderManager.placeOrder(order));
        orderManager.persistOrders();

        System.out.println("Before: total payment: " + orderManager.calculateTotalPayment());
        System.out.println("Before: database: " + orderManager.getDatabase().getOrdersDatabase());
        System.out.println("End ======");

        OrderManagerFacade orderManagerFacade = new OrderManagerFacade();
        orders.forEach(order -> orderManagerFacade.placeOrder(order));
        orderManagerFacade.persistOrders();
        System.out.println("After: total payment: " + orderManagerFacade.calculateTotalPayment());
        System.out.println("After: database: " + orderManagerFacade.getOrderPersister().getDatabase().getOrdersDatabase().toString());
        System.out.println("End ======");
    }
}