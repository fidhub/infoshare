package infoshare.domain;

import java.io.Serializable;

/**
 * Created by user9 on 2015/07/24.
 */
public class Address implements Serializable,Comparable<Address> {

    private String id;
    private String postalAddress;
    private String physicalAddress;
    private String postalCode;
    private String addressType;

    private Address(){

    }

    public Address(Builder builder) {
        this.id = builder.id;
        this.postalAddress = builder.postalAddress;
        this.physicalAddress = builder.physicalAddress;
        this.postalCode = builder.postalCode;
        this.addressType = builder.addressType;
    }

    public String getId() {
        return id;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public static class Builder{
        private String id;
        private String postalAddress;
        private String physicalAddress;
        private String postalCode;
        private String addressType;
        public Builder (String physicalAddress){
            this.physicalAddress = physicalAddress;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder postalAddress(String postalAddress){
            this.postalAddress = postalAddress;
            return this;
        }

        public Builder postalcode(String postalCode){
            this.postalAddress = postalCode;
            return this;
        }
        public Builder addressType(String addressType){
            this.addressType = addressType;
            return this;
        }

        public Builder copy(Address address){
            this.id = address.id;
            this.postalAddress = address.postalAddress;
            this.physicalAddress = address.physicalAddress;
            this.postalCode = address.postalCode;
            this.addressType = address.addressType;

            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return !(id != null ? !id.equals(address.id) : address.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Address address) {
        return id.compareTo(address.id);
    }
}
