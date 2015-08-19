package infoshare.client.content.setup.models;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Created by user9 on 2015/07/24.
 */
public class ContactModel implements Serializable {

    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String contactType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
}
