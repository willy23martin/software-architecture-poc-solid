package org.architecture.solid.principles.poc.ocp.after;

import org.architecture.solid.principles.poc.ocp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRegister {

    private List<Order> orders = new ArrayList<>();

    // Actor#1: Customer: is the responsible to place an order.
    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
