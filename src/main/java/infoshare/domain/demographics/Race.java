package infoshare.domain.demographics;

import java.io.Serializable;

/**
 * Created by user9 on 2016/03/02.
 */
public class Race implements Serializable,Comparable<Race> {

    private String id;
    private String name;
    private String state;

    private Race(){}
    public Race(Builder builder) {
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
        public Builder copy(Race race){
            this.id = race.id;
            this.name = race.name;
            this.state = race.state;

            return this;
        }
        public Race build(){return new Race(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Race race) {
        return name.compareTo(race.name);
    }
}
