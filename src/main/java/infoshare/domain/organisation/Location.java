package infoshare.domain.organisation;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class Location implements Serializable,Comparable<Location> {
   private String org;
   private String id;
   private String name;
   private String locationTypeId;
   private String code;
   private String latitude;
   private String longitude;
   private String parentId;
   private String state;
   private Date date;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(String locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Location() {
    }

    public Location(Builder builder) {
        this.org = builder.org;
        this.id = builder.id;
        this.name = builder.name;
        this.code = builder.code;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.locationTypeId = builder.locationTypeId;
        this.parentId = builder.parentId;
        this.state = builder.state;
        this.date = builder.date;
    }

    public static class Builder{
        private String org;
        private String id;
        private String name;
        private String locationTypeId;
        private String code;
        private String latitude;
        private String longitude;
        private String parentId;
        private String state;
        private Date date;


        public Builder org(String org) {
            this.org = org;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder locationtypeid(String locationTypeId) {
            this.locationTypeId = locationTypeId;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder latitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder parentid(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder copy(Location location){
            this.org = location.org;
            this.id = location.id;
            this.name = location.name;
            this.code = location.code;
            this.latitude = location.latitude;
            this.longitude = location.longitude;
            this.locationTypeId = location.locationTypeId;
            this.parentId = location.parentId;
            this.state = location.state;
            this.date = location.date;
            return this;
        }
        public Location build(){return new Location(this);}
    }

    public static Builder builder(){return new Builder();}

    @Override
    public int compareTo(Location location) {
        return name.compareTo(location.name);
    }
}
