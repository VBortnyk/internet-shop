package dev.internet.shop.service.impl;

import dev.internet.shop.dao.OrderDao;
import dev.internet.shop.lib.Inject;
import dev.internet.shop.lib.Service;
import dev.internet.shop.service.OrderService;
import dev.internet.shop.service.ShoppingCartService;
import dev.internet.shop.model.Order;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    public void completeOrder(ShoppingCart shoppingCart) {
        Long userId = shoppingCart.getUserId();
        List<Product> allProducts = new ArrayList<>(shoppingCart.getProducts());
        shoppingCartService.clear(shoppingCart);
        orderDao.create(new Order(userId, allProducts));
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user.getId());
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
