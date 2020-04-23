package mate.academy.internetshop.model;

import mate.academy.internetshop.db.Storage;

public class Item {
    private Long id;
    private String name;
    private Double price;

    public Item(String name, Double price) {
        this.id = ++Storage.idGenerator;
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return "name: " + name
                + ", price: " + price
                + ", id: " + id;
    }
}
