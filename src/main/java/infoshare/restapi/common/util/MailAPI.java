package infoshare.restapi.common.util;



import infoshare.app.conf.RestUtil;
import infoshare.domain.util.Mail;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/28.
 */
public class MailAPI {
    public static Mail save(Mail mail) {
        return RestUtil.save(CommonBaseURI.Mail.POST, mail, Mail.class);
    }

    public static Mail findById(String org ,String id) {
        return RestUtil.getById(CommonBaseURI.Mail.GET_ID,org+"/"+ id, Mail.class);

    }
    public static Set<Mail> findAll(String org) {
        return RestUtil.getAll(CommonBaseURI.Mail.GETALL+org, Mail.class);
    }
}
