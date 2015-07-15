package infoshare.domain;

import java.io.Serializable;

/**
 * Created by codex on 2015/07/07.
 */
public class Lesson implements Serializable, Comparable<Lesson> {

    private String id;
    private String lesson;
    private String description;
    private String content;

    public Lesson(Builder builder) {
        this.id = builder.id;
        this.lesson = builder.lesson;
        this.description = builder.description;
        this.content = builder.content;
    }

    public String getId() {
        return id;
    }

    public String getLesson() {
        return lesson;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Lesson lesson) {
        return id.compareTo(lesson.id);
    }

    public static class Builder{
        private String id;
        private String lesson;
        private String description;
        private String content;

        public Builder(String lesson){
            this.lesson = lesson;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Lesson build(){
            return new Lesson(this);
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        return !(id != null ? !id.equals(lesson.id) : lesson.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
