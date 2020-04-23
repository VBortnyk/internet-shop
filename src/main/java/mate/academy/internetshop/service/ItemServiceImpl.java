package mate.academy.internetshop.service;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class ItemServiceImpl implements ItemService {
    @Inject
    private ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public List<Item> getAllAvailable() {
        return itemDao.getAll().stream()
                .filter(i -> i.getCount() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public boolean delete(Long id) {
        return itemDao.delete(id);
    }

}
