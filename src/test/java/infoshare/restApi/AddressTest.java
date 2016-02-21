package infoshare.restApi;

import infoshare.restapi.RestApiConnectorClass;
import infoshare.restapi.UrlPath;
import infoshare.client.content.setup.models.AddressModel;
import org.junit.Test;

/**
 * Created by user9 on 2015/08/04.
 */
public class AddressTest {

    //Todo: id=66d6403b1a4c582eb61d2e0f104d4386


    @Test
    public void create() throws Exception {
        AddressModel model = new AddressModel();
        model.setPostalCode("7530");
        model.setPostalAddress("P.O Box 659");
        model.setPhysicalAddress("24 Killernay Street Oakdale Bellville");
        model.setAddressType("address");

        RestApiConnectorClass.create(UrlPath.AddressLinks.POST, model, AddressModel.class);
    }
}
