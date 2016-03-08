package infoshare.restapi.common.demographics;


import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Language;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class LanguageAPI {
    public static Language save(Language language) {
        RestUtil.save(CommonBaseURI.Language.POST, language, Language.class);
        return language;
    }

    public static Language findById(String id) {
        return RestUtil.getById(CommonBaseURI.Language.GET_ID, id, Language.class);

    }

    public static Set<Language> findAll() {
        return RestUtil.getAll(CommonBaseURI.Language.GETALL, Language.class);
    }
}
