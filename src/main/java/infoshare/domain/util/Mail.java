package infoshare.domain.util;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hashcode on 2015/11/28.
 */
public class Mail implements Serializable,Comparable<Mail> {
    private String id;
    private String key;
    private String value;
    private String host;
    private String port;
    private String state;
    private Date date;
    private String orgId;
    private Mail(){}

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getOrgId() {
        return orgId;
    }

    public Mail(Builder builder) {
        this.id = builder.id;
        this.orgId = builder.orgId;
        this.date = builder.date;
        this.host = builder.host;
        this.key = builder.key;
        this.value = builder.value;
        this.port = builder.port;
        this.state = builder.state;
        this.date = builder.date;
    }



    public static class Builder {
        private String id;
        private String key;
        private String value;
        private String host;
        private String port;
        private String state;
        private Date date;
        public String orgId;

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder key(String value) {
            this.key = value;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder host(String value) {
            this.host = value;
            return this;
        }

        public Builder port(String value) {
            this.port = value;
            return this;
        }

        public Builder state(String value) {
            this.state = value;
            return this;
        }

        public Builder date(Date value) {
            this.date = value;
            return this;
        }

        public Builder orgId(String orgId){
            this.orgId = orgId;
            return  this;
        }
        public Builder copy(Mail value) {
            this.id = value.id;
            this.orgId = value.orgId;
            this.date = value.date;
            this.host = value.host;
            this.key = value.key;
            this.value = value.value;
            this.port = value.port;
            this.state = value.state;
            this.date = value.date;
            return this;
        }

        public Mail build() {
            return new Mail(this);
        }
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Mail mail) {
        return host.compareTo(mail.host);
    }
}
