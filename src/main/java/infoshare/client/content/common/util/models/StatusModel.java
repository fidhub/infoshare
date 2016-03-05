package infoshare.client.content.common.util.models;

import java.io.Serializable;

/**
 * Created by bulelani on 2015/09/19.
 */
public class StatusModel implements Serializable {
    private String id;
    private String name;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
