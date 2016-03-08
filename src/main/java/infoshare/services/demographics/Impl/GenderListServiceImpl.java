package infoshare.services.demographics.Impl;


import infoshare.domain.demographics.Gender;
import infoshare.restapi.common.demographics.GenderAPI;
import infoshare.services.demographics.GenderListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class GenderListServiceImpl implements GenderListService {


    @Override
    public Gender findById(String s) {
        return GenderAPI.findById(s);
    }

    @Override
    public Gender save(Gender entity) {
        return GenderAPI.save(entity);
    }

    @Override
    public Gender update(Gender entity) {
        return GenderAPI.save(entity);
    }

    @Override
    public void delete(Gender entity) {
        GenderAPI.save(entity);
    }

    @Override
    public Set<Gender> findAll() {
        return GenderAPI.findAll();
    }
}
