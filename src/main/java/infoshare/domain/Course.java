package infoshare.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by codex on 2015/07/07.
 */
public class Course implements Serializable, Comparable<Course> {

    private String id;
    private String courseLevel;
    private String courseDescription;
    private List<Lesson> lessons = new ArrayList<>();

    private Course() {
    }

    public Course(Builder builder) {
        this.id =builder.id;
        this.courseDescription = builder.courseDescription;
        this.courseLevel = builder.courseLevel;
        this.lessons = builder.lessons;
    }

    public String getId() {
        return id;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public int compareTo(Course course) {
        return id.compareTo(course.id);
    }

    public static class Builder{
        private String id;
        private String courseLevel;
        private String courseDescription;
        private List<Lesson> lessons = new ArrayList<>();

        public Builder(String courseLevel){
            this.courseLevel = courseLevel;
        }
        public Builder id(String id){
            this.id = id;
            return  this;
        }
        public Builder description(String courseDescription){
            this.courseDescription = courseDescription;
            return  this;
        }
        public Builder lessons(List<Lesson> lessons){
            this.lessons = lessons;
            return this;
        }

        public Course build(){
            return new Course(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return !(id != null ? !id.equals(course.id) : course.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
