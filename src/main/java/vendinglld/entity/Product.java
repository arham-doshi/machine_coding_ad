package vendinglld.entity;

public class Product {
    Integer productId;
    String ProductName;
    Integer productPrice;

    public Product(Integer productId, String productName, Integer productPrice) {
        this.productId = productId;
        ProductName = productName;
        this.productPrice = productPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", ProductName='" + ProductName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
