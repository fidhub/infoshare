package infoshare.services.demographics.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.demographics.Language;
import infoshare.restapi.demographics.LanguageAPI;
import infoshare.services.demographics.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-04.
 */
@SpringComponent
@Service
public class LanguageServiceImpl implements LanguageService {
    @Override
    public Language findById(String s) {
        return LanguageAPI.findById(s);
    }

    @Override
    public Language save(Language entity) {
        return LanguageAPI.save(entity);
    }

    @Override
    public Language update(Language entity) {
        return LanguageAPI.save(entity);
    }

    @Override
    public void delete(Language entity) {

    }

    @Override
    public Set<Language> findAll() {
        return LanguageAPI.findAll();
    }
}