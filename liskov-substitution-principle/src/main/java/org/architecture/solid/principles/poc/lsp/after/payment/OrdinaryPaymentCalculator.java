package org.architecture.solid.principles.poc.lsp.after.payment;

import org.architecture.solid.principles.poc.lsp.model.Order;

import java.util.List;

public class OrdinaryPaymentCalculator extends PaymentCalculator {


    @Override
    public double calculateTotalPayment(List<Order> orders) {
        return orders.stream().map(order -> order.getPrice()).reduce(0.0, Double::sum);
    }
}
