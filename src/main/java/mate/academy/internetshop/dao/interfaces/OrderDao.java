package mate.academy.internetshop.dao.interfaces;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

public interface OrderDao {

    Order create(Order order);

    Optional<Order> get(Long orderId);

    List<Order> getUserOrders(Long userId);

    Order update(Order order);

    boolean delete(Long orderId);

    boolean delete(Order order);

    List<Order> getAll();
}

