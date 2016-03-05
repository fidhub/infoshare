package infoshare.services.location.Impl;


import infoshare.domain.location.AddressType;
import infoshare.restapi.common.location.AddressTypeAPI;
import infoshare.services.location.AddressTypeService;

import java.util.Set;

/**
 * Created by garran on 2015/09/06.
 */
public class AddressTypeServiceImpl implements AddressTypeService {


    @Override
    public AddressType findById(String id) {
        return AddressTypeAPI.findById(id);
    }

    @Override
    public AddressType save(AddressType entity) {
        return AddressTypeAPI.save(entity);
    }

    @Override
    public AddressType update(AddressType entity) {
        return AddressTypeAPI.save(entity);
    }

    @Override
    public void delete(AddressType entity) {
        AddressTypeAPI.save(entity);

    }

    @Override
    public Set<AddressType> findAll() {
        return AddressTypeAPI.findAll();
    }

}
