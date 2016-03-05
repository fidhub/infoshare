package infoshare.services.utililties.Impl;


import infoshare.domain.util.Status;
import infoshare.restapi.common.util.StatusAPI;
import infoshare.services.utililties.StatusService;

import java.util.Set;

/**
 * Created by garran on 2015/09/14.
 */
public class StatusServiceImpl implements StatusService {

    @Override
    public Status findById(String id) {
        return StatusAPI.findById(id);
    }

    @Override
    public Status save(Status entity) {
        return StatusAPI.save(entity);
    }

    @Override
    public Status update(Status entity) {
        return StatusAPI.save(entity);
    }

    @Override
    public void delete(Status entity) {
        StatusAPI.save(entity);

    }

    @Override
    public Set<Status> findAll() {
        return StatusAPI.findAll();
    }
}
