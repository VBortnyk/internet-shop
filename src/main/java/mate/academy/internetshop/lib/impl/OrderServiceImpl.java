package mate.academy.internetshop.lib.impl;

import java.util.List;

import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.lib.injector.Inject;
import mate.academy.internetshop.lib.injector.Service;
import mate.academy.internetshop.lib.interfaces.OrderService;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = orderDao.create(products, user);
        return order;
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