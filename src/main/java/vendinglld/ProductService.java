package vendinglld;

import vendinglld.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    Map<Integer, Product> productMap;
    Map<Integer, Integer> inventory;

    public ProductService() {
        this.productMap = new HashMap<>();
        this.inventory = new HashMap<>();
    }

    public void addProduct(Integer productId, String productName, Integer productPrice, Integer quantity) {
        if(productMap.containsKey(productId)){
            // product already exists!
            // we will return error
            throw new IllegalArgumentException("Product already exists : "+productId);
        }
        Product product = new Product(productId, productName, productPrice);
        this.productMap.put(productId, product);
        this.inventory.put(productId, quantity);
    }
    public void addInventory(Integer productId, Integer quantity){
        if(this.productMap.containsKey(productId)){
            this.inventory.put(productId, this.inventory.get(productId) + quantity);
        }
        else{
            throw new IllegalArgumentException("Product not present for inventory addition ; "+ productId);
        }
    }
    public Integer checkoutProduct(Integer productId, Integer currentMoney){
        if(!this.productMap.containsKey(productId)) {
            throw new IllegalArgumentException("Product Id : "+productId+" not found while checking out");
        }
        Integer inventoryCount = this.inventory.get(productId);
        if(inventoryCount == null || inventoryCount <= 0){
            throw new IllegalStateException("Product Id : "+productId+" is out of stock");
        }
        if(this.productMap.get(productId).getProductPrice() > currentMoney){
            throw  new IllegalStateException("Insufficient money for productId : "+productId);
        }
        this.inventory.put(productId,  this.inventory.get(productId)-1);
        return currentMoney - this.productMap.get(productId).getProductPrice();
    }

    public Product getProduct(Integer productId) {
        if(!this.productMap.containsKey(productId)) {
            throw new IllegalArgumentException("Product Id : "+productId+" not found");
        }
        return this.productMap.get(productId);
    }

}
