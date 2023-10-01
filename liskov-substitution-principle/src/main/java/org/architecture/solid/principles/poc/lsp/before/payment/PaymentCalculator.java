package org.architecture.solid.principles.poc.lsp.before.payment;

import org.architecture.solid.principles.poc.lsp.model.Order;

import java.util.List;

public class PaymentCalculator {

    // Actor#2: FinancialDepartment: is the responsible to obtain the total payment to be reported to the user.
    public double calculateTotalPayment(List<Order> orders){
        return orders.stream().map(order -> order.getPrice()).reduce(0.0, Double::sum);
    }

}
