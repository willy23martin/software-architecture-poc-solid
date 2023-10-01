package org.architecture.solid.principles.poc.lsp.after;

import org.architecture.solid.principles.poc.lsp.after.database.IDatabase;
import org.architecture.solid.principles.poc.lsp.after.payment.PaymentCalculator;
import org.architecture.solid.principles.poc.lsp.model.Order;

public class OrderManagerFacade {

    private final OrderRegister orderRegister = new OrderRegister();
    private PaymentCalculator paymentCalculator;

    private OrderPersister orderPersister;

    public OrderManagerFacade(IDatabase database, PaymentCalculator paymentCalculator) {
        this.orderPersister = new OrderPersister(database);
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
