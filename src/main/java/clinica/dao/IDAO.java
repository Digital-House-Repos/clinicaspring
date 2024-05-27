package clinica.dao;

import java.util.ArrayList;

public interface IDAO<T> {
    T create(T t);

    T findById(int id);

    ArrayList<T> findAll();

    T update(T t, Integer id);

    boolean delete(int id);
}
