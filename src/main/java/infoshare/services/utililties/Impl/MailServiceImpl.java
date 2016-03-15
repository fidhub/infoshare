package infoshare.services.utililties.Impl;



import infoshare.domain.util.Mail;
import infoshare.restapi.common.util.MailAPI;
import infoshare.services.utililties.MailService;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/28.
 */
public class MailServiceImpl implements MailService {
    private static MailServiceImpl mailService =null;

    private MailServiceImpl(){}

    public  static MailServiceImpl getInstance(){
        if(mailService ==null) {
            return new MailServiceImpl();
        }
        return mailService;
    }
    @Override
    public Mail findById(String org,String id) {
        return MailAPI.findById(org,id);
    }

    @Override
    public Mail findById(String s) {
        return null;
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
        return null;
    }

    @Override
    public Set<Mail> findAll(String org) {
        return MailAPI.findAll(org);
    }


}
