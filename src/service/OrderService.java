package service;
import db.OrderPendingQueue;
import db.OrderRepository;
import handler.OrderExpireHandler;
import model.Order;
import model.OrderStatus;
import model.Product;

public class OrderService {
    OrderRepository orderRepository = OrderRepository.getINSTANCE();
    OrderExpireHandler orderExpireHandler;
    OrderPendingQueue orderPendingQueue = OrderPendingQueue.getInstance();
    InventoryService inventoryService;

    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        orderExpireHandler = new OrderExpireHandler(orderPendingQueue);
        new Thread(orderExpireHandler).start();
    }


    public void confirmOrder(String orderId) {
        Order order = orderRepository.getOrder(orderId);
        Product product = inventoryService.getProduct(order.getPid());
        if(hasNotExpired(order)){
            product.confirmInventory(order.getCount());
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderExpireHandler.wakeUpIfNeeded();
        }
       System.out.println("Order expired!");
    }

    private boolean hasNotExpired(Order peek) {
        if (System.currentTimeMillis() - peek.getTimestamp() < 3000) {
            return true;
        }
        return false;
    }
    public void placeOrder(String pid, Integer count, String orderId) {
        Order order = new Order(pid, count, orderId);
        Product product = inventoryService.getProduct(order.getPid());
        if(product.blockInventory(order.getCount())){
            orderRepository.addOrder(order);
            orderPendingQueue.addOrder(order);
            orderExpireHandler.wakeUpIfNeeded();
        }
    }
}
