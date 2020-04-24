package mate.academy.internetshop.dao.interfaces;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Order create(List<Product> products, User user);

    Optional<Order> get(Long orderId);

    List<Order> getUserOrders(Long userId);

    Order update(Order order);

    boolean delete(Long orderId);

    boolean delete(Order order);

    List<Order> getAll();
}

