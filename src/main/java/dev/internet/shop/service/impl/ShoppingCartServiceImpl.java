package dev.internet.shop.service.impl;

import dev.internet.shop.dao.ShoppingCartDao;
import dev.internet.shop.lib.Inject;
import dev.internet.shop.lib.Service;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public void addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public void deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).get();
    }

    @Override
    public List<Product> getAllProducts(Long cartId) {
        return shoppingCartDao.getAllProducts(cartId);
    }

    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartDao.get(id).get();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }

    @Override
    public boolean delete(Long shoppingCartId) {
        return shoppingCartDao.delete(shoppingCartId);
    }
}
