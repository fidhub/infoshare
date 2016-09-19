package infoshare.viewUI.container.setup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by user9 on 2015/07/24.
 */
public class AddressModel implements Serializable {

    @NotNull
    private String postalAddress;
    @NotNull
    private String physicalAddress;
    @NotNull
    private String postalCode;
    @NotNull
    private String addressType;

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
