package dev.internet.shop.dao;

import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {

    Optional<ShoppingCart> getByUserId(Long userId);

    boolean delete(ShoppingCart shoppingCart);

    List<Product> getAllProducts(Long cartId);
}
