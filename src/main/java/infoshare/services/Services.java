package infoshare.services;

import java.util.Set;

/**
 * Created by hashcode on 2015/06/24.
 */
public interface Services<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    void delete(E entity);

    Set<E> findAll();
}