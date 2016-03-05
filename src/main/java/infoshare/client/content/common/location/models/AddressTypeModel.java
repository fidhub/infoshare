package infoshare.client.content.common.location.models;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/09/07.
 */
public class AddressTypeModel implements Serializable {
    private String addressTypeName;

    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

}
