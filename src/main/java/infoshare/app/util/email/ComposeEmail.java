package infoshare.app.util.email;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/29.
 */
public class ComposeEmail {
    private String from;
    private String subject;
    private Set<String> addressesTo;
    private Set<String> addressesCC;
    private Set<String> addressesBCC;
    private String password;
    private String body;

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public Set<String> getAddressesTo() {
        return addressesTo;
    }

    public Set<String> getAddressesCC() {
        return addressesCC;
    }

    public Set<String> getAddressesBCC() {
        return addressesBCC;
    }

    public String getPassword() {
        return password;
    }

    public String getBody() {
        return body;
    }

    public ComposeEmail(Builder builder) {
        this.from = builder.from;
        this.subject = builder.subject;
        this.addressesTo = builder.addressesTo;
        this.addressesCC = builder.addressesCC;
        this.addressesBCC = builder.addressesBCC;
        this.password = builder.password;
        this.body = builder.body;

    }

    public static class Builder {
        private String from;
        private String subject;
        private Set<String> addressesTo;
        private Set<String> addressesCC;
        private Set<String> addressesBCC;
        private String password;
        private String body;

        public Builder() {


        }

        public Builder from(String value) {
            this.from = value;
            return this;
        }

        public Builder subject(String value) {
            this.subject = value;
            return this;
        }

        public Builder password(String value) {
            this.password = value;
            return this;
        }

        public Builder body(String value) {
            this.body = value;
            return this;
        }

        public Builder addressesTo(Set<String> value) {
            this.addressesTo = value;
            return this;
        }

        public Builder addressesCC(Set<String> value) {
            this.addressesCC = value;
            return this;
        }

        public Builder addressesBCC(Set<String> value) {
            this.addressesBCC = value;
            return this;
        }

        public Builder copy(ComposeEmail value) {
            this.from = value.from;
            this.subject = value.subject;
            this.addressesTo = value.addressesTo;
            this.addressesCC = value.addressesCC;
            this.addressesBCC = value.addressesBCC;
            this.password = value.password;
            this.body = value.body;
            return this;
        }

        public ComposeEmail build() {
            return new ComposeEmail(this);
        }

    }

}

