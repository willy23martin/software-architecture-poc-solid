package org.architecture.solid.principles.poc.srp.before;

import org.architecture.solid.principles.poc.srp.model.Database;
import org.architecture.solid.principles.poc.srp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> orders = new ArrayList<>();
    private Database database = new Database();

    // Actor#1: Customer: is the responsible to place an order.
    public void placeOrder(Order order) {
        orders.add(order);
    }

    // Actor#2: FinancialDepartment: is the responsible to obtain the total payment to be reported to the user.
    public double calculateTotalPayment(){
        return orders.stream().map(order -> order.getPrice()).reduce(0.0, Double::sum);
    }

    // Actor#3: Database Administrator: is the responsible to persists the orders and their information in the database.
    public void persistOrders() {
        orders.forEach(order -> database.getOrdersDatabase().put(order.getId(), order));
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Database getDatabase() {
        return database;
    }
}
