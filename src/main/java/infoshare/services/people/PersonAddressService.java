package infoshare.services.people;

import infoshare.domain.person.PersonAddress;
<<<<<<< HEAD
=======
import infoshare.domain.person.PersonContact;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Component
public interface PersonAddressService {
    PersonAddress save(PersonAddress entity);
    PersonAddress update(PersonAddress entity);
    void delete(PersonAddress entity);
    PersonAddress findById(String personId, String id);
    Set<PersonAddress> findAll(String personId);
}
