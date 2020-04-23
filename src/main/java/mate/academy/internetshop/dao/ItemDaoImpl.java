package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item update(Item item) {
        IntStream.range(0, Storage.items.size())
                .filter(ind -> Storage.items.get(ind).getId().equals(item.getId()))
                .findFirst()
                .ifPresent(ind -> Storage.items.set(ind, item));
        return item;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.items.removeIf(item -> item.getId().equals(id));
    }
}
