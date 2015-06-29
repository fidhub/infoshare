package infoshare.domain;

import com.google.common.collect.ImmutableSet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hashcode on 2015/04/15.
 */
public class User implements Serializable, Comparable<User> {

    private String id;
    private String firstname;
    private String lastname;
    private String othername;
    private String password;
    private Set<Role> role = new HashSet<>();
    private String username;
    private boolean enable;


    private User() {
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.othername = builder.othername;
        this.password = builder.password;
        this.role = builder.role;
        this.username = builder.username;
//        this.demography = builder.demography;
        this.enable = builder.enable;

    }

    @Override
    public int compareTo(User o) {
        return firstname.compareToIgnoreCase(o.firstname);
    }

    public static class Builder {
        private String id;
        private String firstname;
        private final String lastname;
        private String othername;

        private boolean enable;
        private String password;
        private Set<Role> role = new HashSet<>();
        private String username;

        public Builder(String lastname) {
            this.lastname = lastname;
        }

        public Builder user(User person) {
            this.id = person.id;
            this.firstname = person.getFirstname();
            this.othername = person.getOthername();
            this.password = person.getPassword();
            this.role = person.getRole();
            this.username = person.getUsername();
            this.enable = person.isEnable();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder firstname(String value) {
            this.firstname = value;
            return this;
        }


        public Builder othername(String value) {
            this.othername = value;
            return this;
        }


        public Builder enable(boolean value) {
            this.enable = value;
            return this;
        }

        public Builder password(String value) {
            this.password = value;
            return this;
        }

        public Builder role(Set<Role> value) {
            this.role = value;
            return this;
        }


        public Builder username(String value) {
            this.username = value;
            return this;
        }



        public User build() {
            return new User(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOthername() {
        return othername;
    }



    public String getPassword() {
        return password;
    }

    public Set<Role> getRole() {
        return ImmutableSet.copyOf(role);
    }

    public String getUsername() {
        return username;
    }



    //    public PersonDemography getDemography() {
//        return demography;
//    }
    public boolean isEnable() {
        return enable;
    }


    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}