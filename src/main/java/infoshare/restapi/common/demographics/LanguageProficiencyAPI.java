package infoshare.restapi.common.demographics;


import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.LanguageProficiency;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class LanguageProficiencyAPI {
    public static LanguageProficiency save(LanguageProficiency languageProficiency) {
        RestUtil.save(CommonBaseURI.LanguageProficiency.POST, languageProficiency, LanguageProficiency.class);
        return languageProficiency;
    }

    public static LanguageProficiency findById(String id) {
        return RestUtil.getById(CommonBaseURI.LanguageProficiency.GET_ID, id, LanguageProficiency.class);

    }

    public static Set<LanguageProficiency> findAll() {
        return RestUtil.getAll(CommonBaseURI.LanguageProficiency.GETALL, LanguageProficiency.class);
    }
}
