package mate.academy.internetshop.dao.interfaces;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart get(Long shoppingCartId);

    Optional<ShoppingCart> getByUserId(Long userId);

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(Long shoppingCartId);

    boolean delete(ShoppingCart shoppingCart);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();

}
