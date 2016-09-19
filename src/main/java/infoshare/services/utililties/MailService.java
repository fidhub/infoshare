package infoshare.services.utililties;


import infoshare.domain.util.Mail;
import infoshare.services.Services;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/28.
 */
public interface MailService extends Services<Mail, String> {
    Set<Mail> findAll(String org);
    Mail findById(String org,String id);
}
