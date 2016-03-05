package infoshare.client.content.profile.contacts.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonAddressModel implements Serializable {

    private String description;
    private String postalCode;
    private String addressTypeId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(String addressTypeId) {
        this.addressTypeId = addressTypeId;
    }
}
