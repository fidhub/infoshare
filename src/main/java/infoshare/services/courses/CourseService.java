package infoshare.services.courses;

import infoshare.domain.Course;
import infoshare.services.Services;
import org.springframework.stereotype.Component;

/**
 * Created by codex on 2015/07/07.
 */
@Component
public interface CourseService extends Services<Course,String> {
}
