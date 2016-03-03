package infoshare.restapi.demographics;

import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Language;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-03.
 */
public class LanguageAPI {
    public static Language save(Language entity){
        return RestUtil.save(DemographicsBaseUrl.Language.POST, entity, Language.class);
    }

    public static Language findById(String id){
        return RestUtil.getById(DemographicsBaseUrl.Language.GET_ID, id, Language.class);
    }

    public static Set<Language> findAll(){
        return RestUtil.getAll(DemographicsBaseUrl.Language.GET_ALL, Language.class);
    }
}
