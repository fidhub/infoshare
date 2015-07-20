package infoshare.client.content.systemValues.models;

import com.vaadin.ui.FormLayout;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryModel implements Serializable {

    private String id;
    @NotNull
    private String name;
    @NotNull
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
