package infoshare.client.content.common.systemValues.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryModel implements Serializable {

    @NotNull
    private String name;
    @NotNull
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
