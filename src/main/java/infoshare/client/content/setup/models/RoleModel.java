package infoshare.client.content.setup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by hashcode on 2015/06/23.
 */
public class RoleModel implements Serializable {
    private String id;
    @NotNull
    private String rolename;
    @NotNull
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
