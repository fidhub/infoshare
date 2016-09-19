package infoshare.services.utililties;

import infoshare.domain.util.Status;

import java.util.Set;

/**
 * Created by user9 on 2016/03/09.
 */
public interface StatusService {
    Status findById(String s);

    Status save(Status entity);

    Set<Status> findAll();
}
