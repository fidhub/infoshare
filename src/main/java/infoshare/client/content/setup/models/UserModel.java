package infoshare.client.content.setup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hashcode on 2015/06/23.
 */
public class UserModel implements Serializable{

   // @NotNull
    private String otherName;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password ;
    private boolean enable;
    @NotNull
    private Set<String> roles = new HashSet<>();
    @NotNull
    private String username;
    //@NotNull
    private List<String> contact = new ArrayList<>();

   // @NotNull
    private List<String> address = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getContact() {
        contact.add("54321");
        return contact;
    }

    public void setContact(List<String> contact) {

        this.contact = contact;
    }

    public List<String> getAddress() {
        address.add("12345");
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }


}