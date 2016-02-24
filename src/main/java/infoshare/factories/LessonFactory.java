package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Lesson;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class LessonFactory {

    public static Lesson getLesson(Map<String,String> lessonVals){
        Lesson lesson = new Lesson.Builder(lessonVals.get("lesson"))
                .id(KeyGenerator.getEntityId())
                .content(lessonVals.get("content"))
                .description(lessonVals.get("description"))
                .build();
        return lesson;
    }
}
