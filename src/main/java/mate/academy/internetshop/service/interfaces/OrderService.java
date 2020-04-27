package mate.academy.internetshop.service.interfaces;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(User user, List<Product> products);

    List<Order> getUserOrders(User user);
}
