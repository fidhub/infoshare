package infoshare.services;

import java.util.Set;

/**
 * Created by hashcode on 2015/06/24.
 */
public interface Services<E, ID> {

    public E findById(ID id);

    public E save(E entity);

    public E update(E entity);

    public void delete(E entity);

    public Set<E> findAll();
}