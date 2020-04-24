package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.interfaces.ShoppingCartDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.injector.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.add(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long shoppingCartId) {
        return Storage.SHOPPINGCARTS.stream()
                .filter(shoppingCart -> shoppingCart.getId().equals(shoppingCartId))
                .findFirst();
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.SHOPPINGCARTS.stream()
                .filter(shoppingCart -> shoppingCart.getUser().getId().equals(userId))
                .findFirst();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.SHOPPINGCARTS.size())
                .filter(index -> Storage.SHOPPINGCARTS.get(index).getId()
                        .equals(shoppingCart.getId()))
                .forEach(index -> Storage.SHOPPINGCARTS.set(index, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(Long shoppingCartId) {
        return Storage.SHOPPINGCARTS.removeIf(shoppingCart -> shoppingCart
                .getId().equals(shoppingCartId));
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return Storage.SHOPPINGCARTS.remove(shoppingCart);
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return Storage.SHOPPINGCARTS.stream()
                .filter(cart -> cart.getId().equals(shoppingCart.getId()))
                .findFirst().get().getProducts();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.SHOPPINGCARTS;
    }
}
