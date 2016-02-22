package infoshare.services;

import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */
public interface Service<E, ID> {

    E find(ID id);

    E save(E entity);

    E merge(E entity);

    void remove(E entity);

    List<E> findAll();
}