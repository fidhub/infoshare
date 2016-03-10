package infoshare.services.location.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.location.ContactType;
import infoshare.restapi.location.ContactTypeAPI;
import infoshare.services.location.ContactTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
@SpringComponent
@Service
public class ContactTypeServiceImpl implements ContactTypeService {

    @Override
    public ContactType findById(String s) {
        return ContactTypeAPI.findById(s);
    }

    @Override
    public ContactType save(ContactType entity) {
        return ContactTypeAPI.save(entity);
    }

    @Override
    public ContactType update(ContactType entity) {
        return ContactTypeAPI.save(entity);
    }

    @Override
    public void delete(ContactType entity) {
        ContactTypeAPI.save(entity);
    }

    @Override
    public Set<ContactType> findAll() {
        return ContactTypeAPI.findAll();
    }
}
