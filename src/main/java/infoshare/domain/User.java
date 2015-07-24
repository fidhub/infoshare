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
    private String firstName;
    private String lastName;
    private String otherName;
    private String password;
    private Set<Role> role = new HashSet<>();
    private String username;
    private boolean enable;


    private User() {
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.otherName = builder.otherName;
        this.password = builder.password;
        this.role = builder.role;
        this.username = builder.username;
        this.enable = builder.enable;

    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnable() {
        return enable;
    }

    @Override
    public int compareTo(User o) {
        return firstName.compareToIgnoreCase(o.firstName);
    }

    public static class Builder {
        private String id;
        private String firstName;
        private final String lastName;
        private String otherName;

        private boolean enable;
        private String password;
        private Set<Role> role = new HashSet<>();
        private String username;

        public Builder(String lastName) {
            this.lastName = lastName;
        }

        public Builder user(User person) {
            this.id = person.id;
            this.firstName = person.firstName;
            this.otherName = person.otherName;
            this.password = person.password;
            this.role = person.role;
            this.username = person.username;
            this.enable = person.enable;
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder firstname(String value) {
            this.firstName = value;
            return this;
        }


        public Builder othername(String value) {
            this.otherName = value;
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


}