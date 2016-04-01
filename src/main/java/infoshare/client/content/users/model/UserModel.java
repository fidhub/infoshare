package infoshare.client.content.users.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by THULEH on 2016/03/31.
 */
public class UserModel implements Serializable {
    private String org;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String emailAddress;
    @NotNull
    private String lastName;
    @NotNull
    private String role;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
