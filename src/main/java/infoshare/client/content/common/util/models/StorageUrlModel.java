package infoshare.client.content.common.util.models;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/01/05.
 */
public class StorageUrlModel implements Serializable {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
