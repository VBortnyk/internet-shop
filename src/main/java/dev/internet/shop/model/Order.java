package dev.internet.shop.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private Long userId;
    private List<Product> products;

    public Order(Long orderId, Long userId, List<Product> products) {
        this.id = orderId;
        this.userId = userId;
        this.products = products;
    }

    public Order(Long userId, List<Product> products) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId())
                && Objects.equals(getUserId(), order.getUserId())
                && Objects.equals(getProducts(), order.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getProducts());
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", userId=" + userId
                + ", products=" + products
                + '}';
    }
}
