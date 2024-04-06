package db;

import model.Order;

import java.util.LinkedList;
import java.util.Queue;

public class OrderPendingQueue {
    Queue<Order> ordersQueue;

    public OrderPendingQueue() {
        this.ordersQueue = new LinkedList<>();
    }

    public void addOrder(Order order){
        ordersQueue.add(order);
    }

    public Order peek(){
        return ordersQueue.peek();
    }

    private static OrderPendingQueue INSTANCE = new OrderPendingQueue();

    public static OrderPendingQueue getInstance() {
        return INSTANCE;
    }

    public int getSize(){
        return ordersQueue.size();
    }

    public Order poll(){
        return ordersQueue.poll();
    }

}
