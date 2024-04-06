package service;

import db.InventoryRepository;
import model.Product;

public class InventoryService {
    InventoryRepository inventoryRepository = InventoryRepository.getINSTANCE();

    public Product getProduct(String pid) {
        return inventoryRepository.getProduct(pid);
    }

    public void createProduct(String productId, String name, int count) {
        Product product = new Product(productId, name, count);
        inventoryRepository.addProduct(product);
    }

    public int getInventory(String productId) {
        Product product = inventoryRepository.getProduct(productId);
        return product.getPendingInventory();
    }

    public int getBlockedInventory(String productId) {
        Product product = inventoryRepository.getProduct(productId);
        return product.getBlockedCount();
    }
}
