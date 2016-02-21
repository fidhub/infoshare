package infoshare.restapi.common.education;

import hashwork.app.conf.RestUtil;
import hashwork.domain.ui.education.EducationType;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class EducationTypeAPI {
    public static EducationType save(EducationType educationType) {
        RestUtil.save(CommonBaseURI.EducationType.POST, educationType, EducationType.class);
        return educationType;
    }

    public static EducationType findById(String id) {
        return RestUtil.getById(CommonBaseURI.EducationType.GET_ID, id, EducationType.class);

    }

    public static Set<EducationType> findAll() {
        return RestUtil.getAll(CommonBaseURI.EducationType.GETALL, EducationType.class);
    }
}
