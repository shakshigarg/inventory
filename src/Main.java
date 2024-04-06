import service.InventoryService;
import service.OrderService;

public class Main {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        OrderService orderService = new OrderService(inventoryService);

        inventoryService.createProduct("p1","product1",10);
        inventoryService.createProduct("p2","product2",10);
        System.out.println(inventoryService.getInventory("p1"));

        System.out.println(inventoryService.getInventory("p1"));

        orderService.placeOrder("p1",4,"o1");
        System.out.println(inventoryService.getInventory("p1"));

        orderService.placeOrder("p2",1,"o2");

        orderService.confirmOrder("o1");
        System.out.println(inventoryService.getInventory("p1"));
        System.out.println("remaining "+inventoryService.getInventory("p1")+" blocked "+inventoryService.getBlockedInventory("p1"));


    }
}