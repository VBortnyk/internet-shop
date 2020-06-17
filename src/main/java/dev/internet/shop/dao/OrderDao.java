package dev.internet.shop.dao;

import dev.internet.shop.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {

    boolean delete(Order order);

    List<Order> getUserOrders(Long id);
}
