package mate.academy.internetshop.lib;

import java.util.List;
import mate.academy.internetshop.model.Item;

public interface ItemService {
    Item create(Item item);

    Item get(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);

}
