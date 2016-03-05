package infoshare.client.content.profile.contacts.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonContactsModel implements Serializable {
    private String addressTypeId;
    private String contactValue;
    private String status;


    public String getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(String addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
