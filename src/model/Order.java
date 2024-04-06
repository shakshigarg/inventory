package model;

public class Order {

    String orderId;
    String pid;
    int count;
    long timestamp;
    OrderStatus orderStatus;


    public Order(String pid, int count, String orderId) {
        this.orderId = orderId;
        this.pid = pid;
        this.count = count;
        this.timestamp = System.currentTimeMillis();
        orderStatus = OrderStatus.PENDING;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
