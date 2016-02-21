package infoshare.services.utililties.Impl;



import infoshare.domain.Mail;
import infoshare.restapi.common.util.MailAPI;
import infoshare.services.utililties.MailService;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/28.
 */
public class MailServiceImpl implements MailService {
    @Override
    public Mail findById(String s) {
        return MailAPI.findById(s);
    }

    @Override
    public Mail save(Mail entity) {
        return MailAPI.save(entity);
    }

    @Override
    public Mail update(Mail entity) {
        return MailAPI.save(entity);
    }

    @Override
    public void delete(Mail entity) {
        MailAPI.save(entity);
    }

    @Override
    public Set<Mail> findAll() {
        return MailAPI.findAll();
    }
}
