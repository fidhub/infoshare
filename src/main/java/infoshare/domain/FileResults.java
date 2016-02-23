package infoshare.domain;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/01/04.
 */
public class FileResults implements Serializable {
    private String id;
    private String url;
    private String size;
    private String mime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
