package infoshare.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Set<String> roles = new HashSet<>();
    private String username;
    private boolean enable;
    private List<String> contact = new ArrayList<>();
    private List<String> address = new ArrayList<>();


    private User() {
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.otherName = builder.otherName;
        this.password = builder.password;
        this.roles = builder.roles;
        this.username = builder.username;
//        this.demography = builder.demography;
        this.enable = builder.enable;

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
        private Set<String> roles = new HashSet<>();
        private String username;
        private List<String> contact = new ArrayList<>();
        private List<String> address = new ArrayList<>();

        public Builder(String lastName) {
            this.lastName = lastName;
        }

        public Builder user(User person) {
            this.id = person.id;
            this.firstName = person.getFirstName();
            this.otherName = person.getOtherName();
            this.password = person.getPassword();
            this.roles = person.getRoles();
            this.username = person.getUsername();
            this.enable = person.isEnable();
            this.contact = person.contact;
            this.address = person.address;
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

        public Builder role(Set<String> value) {
            this.roles = value;
            return this;
        }

        public Builder contact(List<String> value){
            this.contact = value;
            return this;
        }

        public Builder address(List<String> address){
            this.address = address;
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

    public Set<String> getRoles() {
        return ImmutableSet.copyOf(roles);
    }

    public List<String> getContact(){
        return ImmutableList.copyOf(contact);
    }
    public List<String> getAddress(){
        return ImmutableList.copyOf(address);
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