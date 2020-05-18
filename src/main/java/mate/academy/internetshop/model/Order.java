package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Product> products;

    public Order(Long orderId, Long userId, List<Product> products) {
        this.id = orderId;
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public Order(Long userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
