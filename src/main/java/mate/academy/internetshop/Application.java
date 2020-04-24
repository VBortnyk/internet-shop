package mate.academy.internetshop;

import mate.academy.internetshop.lib.injector.Injector;
import mate.academy.internetshop.lib.interfaces.OrderService;
import mate.academy.internetshop.lib.interfaces.ProductService;
import mate.academy.internetshop.lib.interfaces.ShoppingCartService;
import mate.academy.internetshop.lib.interfaces.UserService;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;

import java.util.Arrays;
import java.util.List;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService itemService = (ProductService) injector.getInstance(ProductService.class);
//        Product milk = new Product("Milk", 100.00);
//        Product bread = new Product("Bread", 80.00);
//        Product butter = new Product("Butter", 40.00);
//
//        itemService.create(milk);
//        itemService.create(bread);
//        itemService.create(butter);
//
//        System.out.println(itemService.get(bread.getId()));
//        System.out.println(itemService.getAll());
//        System.out.println(itemService.delete(butter.getId()));
//        System.out.println(itemService.getAll());
//
//        Product oil = new Product("oil", 300.00);
//
//        System.out.println(itemService.delete(oil.getId()));
//        System.out.println(itemService.getAll());
//
//        Product grapes = new Product("Grapes", 22.00);
//        itemService.update(grapes);
//
//        System.out.println(itemService.getAll());
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ShoppingCartService cartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        checkProductDB(productService);
        checkUserDB(userService);

        productService.getAll().forEach(System.out::println);
        userService.getAll().forEach(System.out::println);
    }

    private static void checkProductDB(ProductService productService) {
        List<Product> list = generateProducts();
        productService.create(list.get(0));
        productService.create(list.get(1));
        productService.create(list.get(2));


        productService.delete(list.get(0).getId());

        list.get(1).setPrice(30.0);
        productService.update(list.get(1));
    }

    private static void checkUserDB(UserService userService) {
        User user1 = new User("Usre1", "login1", "one");
        User user2 = new User("User2", "login2", "two");

        userService.create(user1);
        userService.create(user2);

        userService.delete(user1.getId());

        user2.setLogin("newLogin");
        userService.update(user2);
    }

    private static List<Product> generateProducts() {
        Product product1 = new Product("Bread", 15.0);
        Product product2 = new Product("Milk", 35.0);
        Product product3 = new Product("Butter", 45.0);
        return Arrays.asList(product1, product2, product3);
    }
}
