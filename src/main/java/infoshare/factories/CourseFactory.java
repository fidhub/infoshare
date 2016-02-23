package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Course;
import infoshare.domain.Lesson;

import java.util.List;
import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class CourseFactory {

    public static Course getCourse(Map<String, String> courseVals, List<Lesson> lessons){
        Course course = new Course.Builder(courseVals.get("courseLevel"))
                .id(KeyGenerator.getEntityId())
                .description(courseVals.get("description"))
                .lessons(lessons)
                .build();
        return course;
    }
}
