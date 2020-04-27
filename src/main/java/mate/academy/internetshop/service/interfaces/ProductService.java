package mate.academy.internetshop.service.interfaces;

import mate.academy.internetshop.model.Product;

public interface ProductService extends GenericService<Product, Long> {
    Product create(Product product);

    Product update(Product product);
}
