package infoshare.domain.demographics;

import java.io.Serializable;

/**
 * Created by user9 on 2016/03/02.
 */
public class Gender implements Serializable,Comparable<Gender> {

    private String id;
    private String name;
    private String state;

    private Gender(){}
    public Gender(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.state = builder.state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private String id;
        private String name;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }
        public Builder copy(Gender gender){
            this.id = gender.id;
            this.name = gender.name;
            this.state = gender.state;

            return this;
        }
        public Gender build(){return new Gender(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Gender gender) {
        return name.compareTo(gender.name);
    }
}
