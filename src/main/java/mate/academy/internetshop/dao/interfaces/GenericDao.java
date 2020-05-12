package mate.academy.internetshop.dao.interfaces;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exceptions.DataBaseAccessException;

public interface GenericDao<T, K> {
    T create(T value) throws DataBaseAccessException;

    Optional<T> get(K value);

    List<T> getAll();

    T update(T value);

    boolean delete(K value);
}

