package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(User user, List<Product> products) {
        Order newOrder = new Order(user, products);
        orderDao.create(newOrder);
        Storage.shoppingCarts.stream()
                .filter(cart -> cart.getUser().getId().equals(user.getId()))
                .findFirst()
                .ifPresent(cart -> cart.getProducts().clear());
        return newOrder;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user.getId());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
