package infoshare.services.demographics.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.demographics.Gender;
import infoshare.restapi.demographics.GenderAPI;
import infoshare.services.demographics.GenderService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-04.
 */
@SpringComponent
@Service
public class GenderServiceImpl implements GenderService {

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

    }

    @Override
    public Set<Gender> findAll() {
        return GenderAPI.findAll();
    }
}