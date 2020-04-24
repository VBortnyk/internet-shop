package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.injector.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(List<Product> products, User user) {
        Order order = new Order();
        order.setProducts(products);
        order.setUser(user);
        Storage.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return Storage.ORDERS.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst();
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.ORDERS.stream()
                .filter(order -> order.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.ORDERS.size())
                .filter(index -> Storage.ORDERS.get(index).getId().equals(order.getId()))
                .forEach(index -> Storage.ORDERS.set(index, order));
        return order;
    }

    @Override
    public boolean delete(Long orderId) {
        return Storage.ORDERS.removeIf(order -> order.getId().equals(orderId));
    }

    @Override
    public boolean delete(Order order) {
        return Storage.ORDERS.remove(order);

    }

    @Override
    public List<Order> getAll() {
        return Storage.ORDERS;
    }
}
