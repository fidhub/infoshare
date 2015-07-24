package infoshare.client.content.systemValues.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by codex on 2015/06/27.
 */
public class ContentTypeModel implements Serializable {

    @NotNull
    private String contentTyeName;
    @NotNull
    private String contentTyeDescription;

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
