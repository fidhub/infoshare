package infoshare.services.courses.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Course;
import infoshare.domain.Lesson;
import infoshare.services.courses.CourseService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by codex on 2015/07/07.
 */
@Service
@SpringComponent
public class Services implements CourseService {

    static Map<String,Course> coursesMap = null;
    public Services() {
        if(coursesMap == null){
            coursesMap = new HashMap<>();
            List<Lesson> lessons = new ArrayList<>();
            Lesson lesson = new Lesson.Builder("Lesson 1")
                    .description("Basics of Health and the CHW")
                    .content("1 Role of the CHW  111 \n" +
                             "2 Learning objectives 112 \n" +
                             "3 What is health? 114 \n" +
                             "4 The human body and how it works 115 \n" +
                             "5 Systems in the human body 120 \n" +
                             "6 What are vital signs? ")
                    .id("1").build();


            lessons.add(lesson);
            Course course = new Course.Builder("Phase 1").lessons(lessons)
                    .description("Community health worker 1").id("1").build();

            List<Lesson> lessons2 = new ArrayList<>();
            Lesson lesson2 = new Lesson.Builder("Lesson 2")
                    .description("Basics of Health and the CHW")
                    .content("1 Role of the CHW 112 \n" +
                            "3 What is health? 114 \n" +
                            "4 The human body and how it works 115 \n" +
                            "5 Systems in the human body 120 \n" +
                            "6 What are vital signs?")
                    .id("2").build();
            lessons2.add(lesson);
            lessons2.add(lesson2);
            Course course1 = new Course.Builder("Phase 2").lessons(lessons2)
                    .description("Community health worker 2").id("2").build();

            coursesMap.put(course.getId(),course);
            coursesMap.put(course1.getId(),course1);


        }
    }

    @Override
    public Course findById(String s) {
        return coursesMap.get(s);
    }

    @Override
    public Course save(Course entity) {
        coursesMap.put(entity.getId(),entity);
        return coursesMap.get(entity.getId());
    }

    @Override
    public Course update(Course entity) {
        coursesMap.put(entity.getId(),entity);
        return coursesMap.get(entity.getId());
    }

    @Override
    public void delete(Course entity) {
        coursesMap.remove(entity.getId());
    }

    @Override
    public Set<Course> findAll() {
        return new HashSet<>(coursesMap.values());
    }
}
