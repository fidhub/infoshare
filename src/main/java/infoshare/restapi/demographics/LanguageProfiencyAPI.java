package infoshare.restapi.demographics;

import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.LanguageProficiency;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-03.
 */
public class LanguageProfiencyAPI {
    public static LanguageProficiency save(LanguageProficiency entity){
        return RestUtil.save(DemographicsBaseUrl.LanguageProficiency.POST, entity, LanguageProficiency.class);
    }

    public static LanguageProficiency findById(String id){
        return RestUtil.getById(DemographicsBaseUrl.LanguageProficiency.GET_ID, id, LanguageProficiency.class);
    }

    public static Set<LanguageProficiency> findAll(){
        return RestUtil.getAll(DemographicsBaseUrl.LanguageProficiency.GET_ALL, LanguageProficiency.class);
    }
}
