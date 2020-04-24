package mate.academy.internetshop.dao.interfaces;

import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> get(Long shoppingCartId);

    Optional<ShoppingCart> getByUserId(Long userId);

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(Long shoppingCartId);

    boolean delete(ShoppingCart shoppingCart);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();

}
