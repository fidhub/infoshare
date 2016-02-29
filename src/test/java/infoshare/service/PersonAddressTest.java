package infoshare.service;

import infoshare.domain.person.PersonAddress;
import infoshare.services.Contact.AddressService;
import infoshare.services.Contact.Impl.AddressServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Songezo on 2015-09-29.
 */
public class PersonAddressTest extends TestCase {

    private AddressService addressService = new AddressServiceImpl();


    @Test
    public void testRead() throws Exception {
        Set<PersonAddress> add = addressService.findAll();
        Assert.assertFalse(add.isEmpty());
    }

    @Test
    public void testName() throws Exception {
        PersonAddress add1 = addressService.findById("2");
        Assert.assertEquals(add1.getPostalAddress(), "4960");
    }
}
