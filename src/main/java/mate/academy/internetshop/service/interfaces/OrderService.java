package mate.academy.internetshop.service.interfaces;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

import java.util.List;

public interface OrderService extends GenericService<Order, Long>{

    Order completeOrder(User user, List<Product> products);

    List<Order> getUserOrders(User user);

    Order get(Long id);

    boolean delete(Long id);
}
