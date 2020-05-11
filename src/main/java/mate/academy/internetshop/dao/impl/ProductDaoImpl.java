//package mate.academy.internetshop.dao.impl;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.IntStream;
//import mate.academy.internetshop.dao.interfaces.ProductDao;
//import mate.academy.internetshop.db.Storage;
//import mate.academy.internetshop.exceptions.DataBaseAccessException;
//import mate.academy.internetshop.lib.Dao;
//import mate.academy.internetshop.model.Product;
//
//
//public class ProductDaoImpl implements ProductDao {
//    @Override
//    public Product create(Product product) throws DataBaseAccessException {
//        Storage.add(product);
//        return product;
//    }
//
//    @Override
//    public Optional<Product> get(Long id) {
//        return Storage.products.stream()
//                .filter(item -> item.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<Product> getAll() {
//        return Storage.products;
//    }
//
//    @Override
//    public Product update(Product product) {
//        IntStream.range(0, Storage.products.size())
//                .filter(index -> Storage.products.get(index).getId().equals(product.getId()))
//                .findFirst()
//                .ifPresent(index -> Storage.products.set(index, product));
//        return product;
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        return Storage.products.removeIf(item -> item.getId().equals(id));
//    }
//}
