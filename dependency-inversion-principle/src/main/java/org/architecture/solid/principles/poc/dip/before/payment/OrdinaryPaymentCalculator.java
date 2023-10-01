package org.architecture.solid.principles.poc.dip.before.payment;

import org.architecture.solid.principles.poc.dip.model.Order;

import java.util.List;

public class OrdinaryPaymentCalculator extends PaymentCalculator {


    @Override
    public double calculateTotalPayment(List<Order> orders) {
        return orders.stream().map(order -> order.getPrice()).reduce(0.0, Double::sum);
    }
}
