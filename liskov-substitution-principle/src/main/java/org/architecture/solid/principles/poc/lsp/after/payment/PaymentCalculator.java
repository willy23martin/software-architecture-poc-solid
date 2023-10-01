package org.architecture.solid.principles.poc.lsp.after.payment;

import org.architecture.solid.principles.poc.lsp.model.Order;

import java.util.List;

public abstract class PaymentCalculator {

    // Actor#2: FinancialDepartment: is the responsible to obtain the total payment to be reported to the user.
    public abstract double calculateTotalPayment(List<Order> orders);

}
