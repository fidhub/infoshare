package infoshare.services;

import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */
public interface Service<E, ID> {

    public E find(ID id);

    public E save(E entity);

    public E merge(E entity);

    public void remove(E entity);

    public List<E> findAll();
}