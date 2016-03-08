package infoshare.services.demographics.Impl;


import infoshare.domain.demographics.LanguageProficiency;
import infoshare.restapi.common.demographics.LanguageProficiencyAPI;
import infoshare.services.demographics.LanguageProficiencyService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageProficiencyServiceImpl implements LanguageProficiencyService {

    @Override
    public LanguageProficiency findById(String s) {
        return LanguageProficiencyAPI.findById(s);
    }

    @Override
    public LanguageProficiency save(LanguageProficiency entity) {
        return LanguageProficiencyAPI.save(entity);
    }

    @Override
    public LanguageProficiency update(LanguageProficiency entity) {
        return LanguageProficiencyAPI.save(entity);
    }

    @Override
    public void delete(LanguageProficiency entity) {
        LanguageProficiencyAPI.save(entity);
    }

    @Override
    public Set<LanguageProficiency> findAll() {
        return LanguageProficiencyAPI.findAll();
    }
}
