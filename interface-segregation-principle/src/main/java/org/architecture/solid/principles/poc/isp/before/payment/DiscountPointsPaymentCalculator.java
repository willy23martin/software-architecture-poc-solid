package org.architecture.solid.principles.poc.isp.before.payment;

import org.architecture.solid.principles.poc.isp.model.Order;

import java.util.List;

public class DiscountPointsPaymentCalculator extends PaymentCalculator {

    private static final double DISCOUNT = 0.80; // 20%

    @Override
    public double calculateTotalPayment(List<Order> orders){
        return orders.stream().map(order -> order.getPrice()).reduce(0.0, Double::sum) * DISCOUNT;
    }

}
