package infoshare.services.Contact.Impl;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.domain.Contact;
import infoshare.services.Contact.ContactService;

import java.util.List;

/**
 * Created by user9 on 2015/07/28.
 */
public class ContactServiceImpl implements ContactService {
    @Override
    public Contact find(String s) {
        return null;
    }

    @Override
    public Contact save(Contact entity) {
        return null;//.create(UrlPath.);
    }

    @Override
    public Contact merge(Contact entity) {
        return null;
    }

    @Override
    public void remove(Contact entity) {

    }

    @Override
    public List<Contact> findAll() {
        return null;
    }
}
