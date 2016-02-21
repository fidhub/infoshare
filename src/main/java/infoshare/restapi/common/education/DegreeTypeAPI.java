package infoshare.restapi.common.education;

import hashwork.app.conf.RestUtil;
import hashwork.domain.ui.education.DegreeType;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/13.
 */
public class DegreeTypeAPI {
    public static DegreeType save(DegreeType evaluation) {
        RestUtil.save(CommonBaseURI.DegreeType.POST, evaluation, DegreeType.class);
        return evaluation;
    }

    public static DegreeType findById(String id) {
        return RestUtil.getById(CommonBaseURI.DegreeType.GET_ID, id, DegreeType.class);

    }

    public static Set<DegreeType> findAll() {
        return RestUtil.getAll(CommonBaseURI.DegreeType.GETALL, DegreeType.class);
    }
}