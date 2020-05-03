package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.add(order);
        return order;
    }

    @Override
    public Order get(Long orderId) {
        return Storage.orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst().get();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(index -> Storage.orders.get(index).getId().equals(order.getId()))
                .forEach(index -> Storage.orders.set(index, order));
        return order;
    }

    @Override
    public boolean delete(Long orderId) {
        return Storage.orders.removeIf(order -> order.getId().equals(orderId));
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);

    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }
}
