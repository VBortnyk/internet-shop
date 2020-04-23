package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        Item milk = new Item("Milk", 100.00, 20);
        Item bread = new Item("Bread", 80.00, 5);
        Item butter = new Item("Butter", 40.00, 7);

        itemService.create(butter);
        itemService.create(bread);
        itemService.create(butter);

        System.out.println(itemService.get(bread.getId()));

        butter.setCount(0);

        System.out.println(itemService.getAll());
        System.out.println(itemService.getAllAvailable());
        System.out.println(itemService.delete(butter.getId()));

        Item oil = new Item("oil", 300.00, 100);

        System.out.println(itemService.delete(oil.getId()));
        System.out.println(itemService.getAll());

        Item grapes = new Item("Grapes", 22.00, 20);
        itemService.update(grapes);

        System.out.println(itemService.getAll());
    }
}
