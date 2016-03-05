package infoshare.client.content.common.location.models;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationTypeModel implements Serializable {
    private String name;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
