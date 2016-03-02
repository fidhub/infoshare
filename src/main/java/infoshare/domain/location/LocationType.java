package infoshare.domain.location;

import java.io.Serializable;

/**
 * Created by user9 on 2016/03/01.
 */
public class LocationType implements Serializable,Comparable<LocationType> {

    private String id;
    private String name;
    private String code;
    private String state;

    private LocationType(){}

    public LocationType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.code = builder.code;
        this.state = builder.state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private String id;
        private String name;
        private String code;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder copy(LocationType locationType){
            this.id = locationType.id;
            this.name = locationType.name;
            this.code = locationType.code;
            this.state = locationType.state;

            return this;
        }
        public LocationType build(){return new LocationType(this);}
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(LocationType locationType) {
        return name.compareTo(locationType.name);
    }
}
