package mate.academy.internetshop.dao.interfaces;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderDao extends GenericDao<Order, Long> {
    public boolean delete(Order order);
    public List<Order> getUserOrders(Long id);
}