package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<Product> products;

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getItems() {
        return products;
    }

    public void setItems(List<Product> items) {
        this.products = items;
    }
}
