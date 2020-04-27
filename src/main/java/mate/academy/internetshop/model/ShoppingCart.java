package mate.academy.internetshop.model;

import java.util.List;

public class ShoppingCart {
    private Long id;
    private List<Product> products;
    private User user;

    public ShoppingCart(Long id, List<Product> products, User user) {
        this.id = id;
        this.products = products;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
