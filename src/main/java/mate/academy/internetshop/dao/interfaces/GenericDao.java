package mate.academy.internetshop.dao.interfaces;

import java.util.List;

public interface GenericDao<T, K> {
    T create(T value);

    T get(K value);

    List<T> getAll();

    T update(T value);

    boolean delete(K value);
}

