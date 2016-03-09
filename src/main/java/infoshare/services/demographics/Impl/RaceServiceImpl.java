package infoshare.services.demographics.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.demographics.Race;
import infoshare.restapi.demographics.RaceAPI;
import infoshare.services.demographics.RaceService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-04.
 */
@SpringComponent
@Service
public class RaceServiceImpl implements RaceService {
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

    }

    @Override
    public Set<Race> findAll() {
        return RaceAPI.findAll();
    }
}