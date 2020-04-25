package mate.academy.internetshop.lib.interfaces;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

public interface OrderService {

    Order completeOrder(User user, List<Product> products);

    List<Order> getUserOrders(User user);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);

}
