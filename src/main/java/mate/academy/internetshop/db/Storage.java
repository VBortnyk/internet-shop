package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {

    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    private static Long productId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long userId = 0L;
    private static Long orderId = 0L;

    public static void add(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void add(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(shoppingCart);
    }

    public static void add(User user) {
        user.setId(++userId);
        users.add(user);
    }

    public static void add(Order order) {
        order.setId(++orderId);
        order.getUser().getOrders().add(order);
        orders.add(order);
    }
}
