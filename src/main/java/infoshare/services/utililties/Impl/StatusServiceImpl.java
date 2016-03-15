package infoshare.services.utililties.Impl;

import infoshare.domain.util.Status;
import infoshare.restapi.common.util.StatusAPI;
import infoshare.services.utililties.StatusService;

import java.util.Set;

/**
 * Created by user9 on 2016/03/09.
 */
public class StatusServiceImpl implements StatusService {
    private static StatusServiceImpl statusService =null;

    private StatusServiceImpl(){}

    public  static StatusServiceImpl getInstance(){
        if(statusService ==null) {
            return new StatusServiceImpl();
        }
        return statusService;
    }
    @Override
    public Status findById(String s) {
        return StatusAPI.findById(s);
    }

    @Override
    public Status save(Status newEntity) {
        return StatusAPI.save(newEntity);
    }

    @Override
    public Set<Status> findAll() {
        return StatusAPI.findAll();
    }
}
