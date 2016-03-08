package infoshare.services.demographics.Impl;


import infoshare.domain.demographics.Language;
import infoshare.restapi.common.demographics.LanguageAPI;
import infoshare.services.demographics.LanguageService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
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
        LanguageAPI.save(entity);

    }

    @Override
    public Set<Language> findAll() {
        return LanguageAPI.findAll();
    }
}
