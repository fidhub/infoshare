package infoshare.restapi.common.education;

import hashwork.app.conf.RestUtil;
import hashwork.domain.ui.education.Evaluation;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class EvaluationAPI {
    public static Evaluation save(Evaluation evaluation) {
        RestUtil.save(CommonBaseURI.Evaluation.POST, evaluation, Evaluation.class);
        return evaluation;
    }

    public static Evaluation findById(String id) {
        return RestUtil.getById(CommonBaseURI.Evaluation.GET_ID, id, Evaluation.class);

    }

    public static Set<Evaluation> findAll() {
        return RestUtil.getAll(CommonBaseURI.Evaluation.GETALL, Evaluation.class);
    }
}
