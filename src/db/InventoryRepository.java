package db;

import model.Product;

import java.util.HashMap;
import java.util.Map;

public class InventoryRepository {
    Map<String, Product> productCountMap;
    private static InventoryRepository INSTANCE = new InventoryRepository();

    private InventoryRepository() {
        this.productCountMap = new HashMap<>();
    }

    public Product getProduct(String pid) {
        return productCountMap.get(pid);
    }

    public void addProduct(Product p) {
        productCountMap.put(p.getPid(), p);
    }

    public static InventoryRepository getINSTANCE() {
        return INSTANCE;
    }
}
