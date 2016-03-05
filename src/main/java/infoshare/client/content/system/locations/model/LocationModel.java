package infoshare.client.content.system.locations.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationModel implements Serializable {
    private String name;
    private String code;
    private String latitude;
    private String longitude;
    private String locationTypeId;
    private List<String> childrenIds; // Province has Cities as Children
    private String parentId; //Location Id of Parent, e.g Country --> Province-->City

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(String locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public List<String> getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(List<String> childrenIds) {
        this.childrenIds = childrenIds;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
