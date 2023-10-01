package org.architecture.solid.principles.poc.ocp.before;

import org.architecture.solid.principles.poc.ocp.model.Order;

public class OrderManagerFacade {

    private final OrderRegister orderRegister = new OrderRegister();
    private final PaymentCalculator paymentCalculator = new PaymentCalculator();
    private final OrderPersister orderPersister = new OrderPersister();

    // Actor#1: Customer: is the responsible to place an order.
    public void placeOrder(Order order) {
        orderRegister.placeOrder(order);
    }

    // Actor#2: FinancialDepartment: is the responsible to obtain the total payment to be reported to the user.
    public double calculateTotalPayment(){
        return paymentCalculator.calculateTotalPayment(orderRegister.getOrders());
    }

    // Actor#3: Database Administrator: is the responsible to persists the orders and their information in the database.
    public void persistOrders() {
        orderPersister.persistOrders(orderRegister.getOrders());
    }

    public OrderRegister getOrderRegister() {
        return orderRegister;
    }

    public PaymentCalculator getPaymentCalculator() {
        return paymentCalculator;
    }

    public OrderPersister getOrderPersister() {
        return orderPersister;
    }
}
