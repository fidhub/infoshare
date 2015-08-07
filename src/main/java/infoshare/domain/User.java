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
    private String otherName;
    private String firstName;
    private String lastName;
    private String username;
    private boolean enable;
    private String password;
    private Set<String> roles = new HashSet<>();
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
        this.contact = builder.contact;
        this.address = builder.address;
//        this.demography = builder.demography;
        this.enable = builder.enable;

    }

    public String getId() {
        return id;
    }

    public String getOtherName() {
        return otherName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public List<String> getContact() {
        return contact;
    }

    public List<String> getAddress() {
        return address;
    }

    public static class Builder {
        private String id;
        private String otherName;
        private String firstName;
        private String lastName;
        private String username;
        private boolean enable;
        private String password;
        private Set<String> roles = new HashSet<>();
        private List<String> contact = new ArrayList<>();
        private List<String> address = new ArrayList<>();

        public Builder(String lastName) {
            this.lastName = lastName;
        }

        public Builder user(User person) {
            this.id = person.id;
            this.firstName = person.firstName;
            this.otherName = person.otherName;
            this.password = person.password;
            this.roles = person.roles;
            this.username = person.username;
            this.enable = person.enable;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return !(id != null ? !id.equals(user.id) : user.id != null);

    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public int compareTo(User user) {
        return id.compareToIgnoreCase(user.id);
    }
}