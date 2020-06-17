package dev.internet.shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {

    T create(T value);

    Optional<T> get(K value);

    List<T> getAll();

    T update(T value);

    boolean delete(K value);
}
