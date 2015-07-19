package infoshare.client.content.systemValues.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by codex on 2015/06/27.
 */
public class ContentTypeModel implements Serializable {

    private String id;
    @NotNull
    private String contentTyeName;
    @NotNull
    private String contentTyeDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentTyeName() {
        return contentTyeName;
    }

    public void setContentTyeName(String contentTyeName) {
        this.contentTyeName = contentTyeName;
    }

    public String getContentTyeDescription() {
        return contentTyeDescription;
    }

    public void setContentTyeDescription(String contentTyeDescription) {
        this.contentTyeDescription = contentTyeDescription;
    }
}
