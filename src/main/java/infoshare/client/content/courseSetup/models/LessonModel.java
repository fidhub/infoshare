package infoshare.client.content.courseSetup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by codex on 2015/07/07.
 */
public class LessonModel implements Serializable {

    private String id;
    @NotNull
    private String lesson;
    @NotNull
    private String description;
    @NotNull
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}