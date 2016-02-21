package infoshare.restapi.common.job;

import hashwork.app.conf.RestUtil;
import hashwork.domain.payroll.ui.job.JobClassification;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class JobClassificationAPI {
    public static JobClassification save(JobClassification jobClassification) {
        RestUtil.save(CommonBaseURI.JobClassification.POST, jobClassification, JobClassification.class);
        return jobClassification;
    }

    public static JobClassification findById(String id) {
        return RestUtil.getById(CommonBaseURI.JobClassification.GET_ID, id, JobClassification.class);

    }

    public static Set<JobClassification> findAll() {
        return RestUtil.getAll(CommonBaseURI.JobClassification.GETALL, JobClassification.class);
    }
}
