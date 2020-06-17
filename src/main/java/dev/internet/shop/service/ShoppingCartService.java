package dev.internet.shop.service;

import dev.internet.shop.lib.Service;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;

import java.util.List;

@Service
public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {

    ShoppingCart create(ShoppingCart shoppingCart);

    void addProduct(ShoppingCart shoppingCart, Product product);

    void deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(Long cartId);

}
