package infoshare.domain.util;

import java.io.Serializable;

/**
 * Created by user9 on 2016/03/01.
 */
public class Status implements Serializable, Comparable<Status> {

    private String id;
    private String name;
    private String value;
    private String state;

    private Status(){}

    public Status(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.value = builder.value;
        this.state = builder.state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private String id;
        private String name;
        private String value;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }
        public Builder copy(Status status){
            this.id = status.id;
            this.name = status.name;
            this.value = status.value;
            this.state = status.state;

            return this;
        }

        public Status build(){return new Status(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Status status) {
        return id.compareTo(status.id);
    }
}
