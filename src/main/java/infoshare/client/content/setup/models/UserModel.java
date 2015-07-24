package infoshare.client.content.setup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by hashcode on 2015/06/23.
 */
public class UserModel implements Serializable, Comparable<UserModel> {

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private String othername;
    private String password;
    @NotNull
    private String email;
    private boolean enabled;
    @NotNull
    private Set<String> roleIds;
    @NotNull
    private String username;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(UserModel o) {
        return this.getLastname().compareTo(o.getLastname());
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the roleIds
     */
    public Set<String> getRoleIds() {
        return roleIds;
    }

    /**
     * @param roleIds the roleIds to set
     */
    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }

}