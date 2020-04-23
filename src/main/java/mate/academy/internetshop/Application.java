package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.lib.ItemService;
import mate.academy.internetshop.model.Item;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        Item milk = new Item("Milk", 100.00);
        Item bread = new Item("Bread", 80.00);
        Item butter = new Item("Butter", 40.00);

        itemService.create(milk);
        itemService.create(bread);
        itemService.create(butter);

        System.out.println(itemService.get(bread.getId()));
        System.out.println(itemService.getAll());
        System.out.println(itemService.delete(butter.getId()));
        System.out.println(itemService.getAll());

        Item oil = new Item("oil", 300.00);

        System.out.println(itemService.delete(oil.getId()));
        System.out.println(itemService.getAll());

        Item grapes = new Item("Grapes", 22.00);
        itemService.update(grapes);

        System.out.println(itemService.getAll());
    }
}
