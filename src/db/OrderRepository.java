package db;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;
import model.Product;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    Map<String, Order> orderMap;
    private static OrderRepository INSTANCE = new OrderRepository();

    private OrderRepository() {
        this.orderMap = new HashMap<>();
    }

    public Order getOrder(String id){
        return orderMap.get(id);
    }

    public void addOrder(Order order){
        orderMap.put(order.getOrderId(),order);
    }


    public static OrderRepository getINSTANCE() {
        return INSTANCE;
    }
}
