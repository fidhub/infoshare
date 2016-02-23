package infoshare.service;

import infoshare.domain.Address;
import infoshare.services.Contact.AddressService;
import infoshare.services.Contact.Impl.AddressServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Songezo on 2015-09-29.
 */
public class AddressTest extends TestCase {

    private AddressService addressService = new AddressServiceImpl();


    @Test
    public void testRead() throws Exception {
        List<Address> add = addressService.findAll();
        Assert.assertFalse(add.isEmpty());
    }

    @Test
    public void testName() throws Exception {
        Address add1 = addressService.find("2");
        Assert.assertEquals(add1.getPostalAddress(), "4960");
    }
}
