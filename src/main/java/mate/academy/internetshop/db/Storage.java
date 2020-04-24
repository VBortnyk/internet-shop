package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {

    private static Long productId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long userId = 0L;
    private static Long orderId = 0L;
    public static final List<Product> PRODUCTS = new ArrayList<>();
    public static final List<ShoppingCart> SHOPPINGCARTS = new ArrayList<>();
    public static final List<User> USERS = new ArrayList<>();
    public static final List<Order> ORDERS = new ArrayList<>();

    public static void add(Product product) {
        product.setId(++productId);
        PRODUCTS.add(product);
    }

    public static void add(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        SHOPPINGCARTS.add(shoppingCart);
    }

    public static void add(User user) {
        user.setId(++userId);
        USERS.add(user);
    }

    public static void add(Order order) {
        order.setId(++orderId);
        ORDERS.add(order);

    }
}
