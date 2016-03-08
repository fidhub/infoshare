package infoshare.services.demographics.Impl;


import infoshare.domain.demographics.Race;
import infoshare.restapi.common.demographics.RaceAPI;
import infoshare.services.demographics.RaceListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class RaceListServiceImpl implements RaceListService {

    @Override
    public Race findById(String s) {
        return RaceAPI.findById(s);
    }

    @Override
    public Race save(Race entity) {
        return RaceAPI.save(entity);
    }

    @Override
    public Race update(Race entity) {
        return RaceAPI.save(entity);
    }

    @Override
    public void delete(Race entity) {
        RaceAPI.save(entity);

    }

    @Override
    public Set<Race> findAll() {
        return RaceAPI.findAll();
    }
}
