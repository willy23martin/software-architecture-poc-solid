package org.architecture.solid.principles.poc.dip.before;

import org.architecture.solid.principles.poc.dip.before.database.IDatabasePersister;
import org.architecture.solid.principles.poc.dip.before.payment.PaymentCalculator;
import org.architecture.solid.principles.poc.dip.model.Order;

public class OrderManagerFacade {

    private final OrderRegister orderRegister = new OrderRegister();
    private PaymentCalculator paymentCalculator;

    private OrderPersister orderPersister;

    public OrderManagerFacade(IDatabasePersister databasePersister, PaymentCalculator paymentCalculator) {
        this.orderPersister = new OrderPersister(databasePersister);
        this.paymentCalculator = paymentCalculator;
    }

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
