package mate.academy.internetshop.dao.interfaces;

import java.util.List;
import mate.academy.internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    public boolean delete(Order order);

    public List<Order> getUserOrders(Long id);
}
