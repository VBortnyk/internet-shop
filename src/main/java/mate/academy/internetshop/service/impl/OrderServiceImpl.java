package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.OrderService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(User user, List<Product> products) {
        Order newOrder = new Order(user, products);
        orderDao.create(newOrder);
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        return orderDao.create(newOrder);
    }

    public Order completeOrder(ShoppingCart shoppingCart) {
        User user = shoppingCart.getUser();
        List<Product> allProducts = new ArrayList<>(shoppingCart.getProducts());
        shoppingCartService.clear(shoppingCart);
        return orderDao.create(new Order(user, allProducts));
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
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