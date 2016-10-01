package infoshare.services.location.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.location.AddressType;
import infoshare.restapi.location.AddressTypeAPI;
import infoshare.services.location.AddressTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
@SpringComponent
@Service
public class AddressTypeServiceImpl implements AddressTypeService {
    private static AddressTypeServiceImpl addressTypeService=null;

    private AddressTypeServiceImpl(){}

    public  static AddressTypeServiceImpl getInstance(){
        if(addressTypeService==null) {
            return new AddressTypeServiceImpl();
        }
        return addressTypeService;
    }
    @Override
    public AddressType findById(String s) {
        return AddressTypeAPI.findById(s);
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
