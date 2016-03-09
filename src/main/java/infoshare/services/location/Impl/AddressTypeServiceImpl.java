package infoshare.services.location.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.location.AddressType;
import infoshare.restapi.location.AddressTypeAPI;
<<<<<<< HEAD
import infoshare.services.location.AddressTypeService;
=======
import infoshare.services.Services;
import infoshare.services.location.AddressTypeService;
import org.springframework.stereotype.Component;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
@SpringComponent
@Service
public class AddressTypeServiceImpl implements AddressTypeService {
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
        return null;
    }

    @Override
    public void delete(AddressType entity) {

    }

    @Override
    public Set<AddressType> findAll() {
        return AddressTypeAPI.findAll();
    }
}
