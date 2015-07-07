package infoshare.client.content.courseSetup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by codex on 2015/07/07.
 */
public class CoursesModel implements Serializable {

    private String id;
    @NotNull
    private String courseLevel;
    @NotNull
    private String courseDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
