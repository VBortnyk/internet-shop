package mate.academy.internetshop.dao.interfaces;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {

    Order create(Order order);

    Order get(Long orderId);

    List<Order> getUserOrders(Long userId);

    Order update(Order order);

    boolean delete(Order order);

    List<Order> getAll();
}
