package mate.academy.internetshop.service.interfaces;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(User user, List<Product> products);

    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(User user);
}
