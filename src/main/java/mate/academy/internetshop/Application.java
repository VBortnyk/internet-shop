package mate.academy.internetshop;

import java.util.Arrays;
import java.util.List;
import mate.academy.internetshop.lib.injector.Injector;
import mate.academy.internetshop.lib.interfaces.OrderService;
import mate.academy.internetshop.lib.interfaces.ProductService;
import mate.academy.internetshop.lib.interfaces.ShoppingCartService;
import mate.academy.internetshop.lib.interfaces.UserService;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import org.w3c.dom.ls.LSOutput;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        System.out.println("Test");
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ShoppingCartService cartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);

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
        User user1 = new User("User1", "login1", "one");
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
