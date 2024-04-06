package handler;


import db.InventoryRepository;
import db.OrderPendingQueue;
import model.Order;
import model.OrderStatus;
import model.Product;

public class OrderExpireHandler implements Runnable {
    OrderPendingQueue orderPendingQueue;
    InventoryRepository inventoryRepository = InventoryRepository.getINSTANCE();
    public OrderExpireHandler(OrderPendingQueue orderPendingQueue) {
        this.orderPendingQueue = orderPendingQueue;
    }

    @Override
    public void run() {
        synchronized (orderPendingQueue) {
            do {
                while (orderPendingQueue.getSize() == 0 || hasNotExpired(orderPendingQueue.peek())) {
                    try {
                        orderPendingQueue.wait(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                Order expiredOrder = orderPendingQueue.poll();
                if(expiredOrder.getOrderStatus()!=OrderStatus.COMPLETED){
                    System.out.println("Order id "+expiredOrder.getOrderId()+" expired!");
                    expiredOrder.setOrderStatus(OrderStatus.EXPIRED);
                    Product product = inventoryRepository.getProduct(expiredOrder.getPid());
                    product.releaseInventory(expiredOrder.getCount());
                }
            } while (true);
        }
    }

    public void wakeUpIfNeeded() {
        synchronized (orderPendingQueue) {
            orderPendingQueue.notifyAll();
        }
    }


    private boolean hasNotExpired(Order peek) {
        if (System.currentTimeMillis() - peek.getTimestamp() < 3000) {
            return true;
        }
        return false;
    }
}
