package dev.internet.shop.service;

import dev.internet.shop.model.Order;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.model.User;

import java.util.List;

public interface OrderService extends GenericService<Order, Long> {

    void completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(User user);
}
