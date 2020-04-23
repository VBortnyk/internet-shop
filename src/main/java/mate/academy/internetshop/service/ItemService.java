package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Item;

public interface ItemService {
    Item create(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    List<Item> getAllAvailable();

    Item update(Item item);

    boolean delete(Long id);

}
