package mate.academy.internetshop.lib.impl;

import mate.academy.internetshop.dao.interfaces.ShoppingCartDao;
import mate.academy.internetshop.lib.injector.Inject;
import mate.academy.internetshop.lib.injector.Service;
import mate.academy.internetshop.lib.interfaces.ShoppingCartService;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart.getId() == null) {
            shoppingCartDao.create(shoppingCart);
        }     shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().removeIf(product1 -> product.getId()
                .equals(product1.getId()));
        shoppingCartDao.update(shoppingCart);
        return true; }

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
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.getAllProducts(shoppingCart);
    }
}
