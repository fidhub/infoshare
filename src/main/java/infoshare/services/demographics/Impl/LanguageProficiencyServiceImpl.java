package infoshare.services.demographics.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.demographics.LanguageProficiency;
import infoshare.restapi.demographics.LanguageProfiencyAPI;
import infoshare.services.demographics.LanguageProficiencyService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-04.
 */
@SpringComponent
@Service
public class LanguageProficiencyServiceImpl implements LanguageProficiencyService {
    private static LanguageProficiencyServiceImpl languageProficiencyService=null;

    private LanguageProficiencyServiceImpl(){}

    public  static LanguageProficiencyServiceImpl getInstance(){
        if(languageProficiencyService==null) {
            return new LanguageProficiencyServiceImpl();
        }
        return  languageProficiencyService;
    }

    @Override
    public LanguageProficiency findById(String s) {
        return LanguageProfiencyAPI.findById(s);
    }

    @Override
    public LanguageProficiency save(LanguageProficiency entity) {
        return LanguageProfiencyAPI.save(entity);
    }

    @Override
    public LanguageProficiency update(LanguageProficiency entity) {
        return LanguageProfiencyAPI.save(entity);
    }

    @Override
    public void delete(LanguageProficiency entity) {

    }

    @Override
    public Set<LanguageProficiency> findAll() {
        return LanguageProfiencyAPI.findAll();
    }
}