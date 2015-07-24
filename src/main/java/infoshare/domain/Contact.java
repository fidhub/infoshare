package infoshare.domain;

import java.io.Serializable;

/**
 * Created by user9 on 2015/07/24.
 */
public class Contact implements Serializable, Comparable<Contact> {

    private String id;
    private String phone;
    private String email;
    private String contactType;

    private Contact() {
    }

    public Contact(Builder builder) {
        this.id = builder.id;
        this.phone = builder.phone;
        this.email = builder.email;
        this.contactType = builder.contactType;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getContactType() {
        return contactType;
    }

    public static class Builder{
        private String id;
        private String phone;
        private String email;
        private String contactType;

        public Builder(String phone){
            this.phone = phone;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder contactType(String contactType){
            this.contactType = contactType;
            return this;
        }

        public Builder copy(Contact contact){
            this.id = contact.id;
            this.phone = contact.phone;
            this.contactType = contact.contactType;
            this.email = contact.email;
            return this;
        }
        public Contact build(){
            return new Contact(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return !(id != null ? !id.equals(contact.id) : contact.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Contact contact) {
        return id.compareTo(contact.id);
    }
}
