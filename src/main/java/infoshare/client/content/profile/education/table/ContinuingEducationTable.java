package infoshare.client.content.profile.education.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.facade.TrainingFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonContinuingEducation;
import hashwork.domain.ui.training.CompetencyEvaluation;
import hashwork.domain.ui.training.Course;
import hashwork.domain.ui.training.ScheduledCourse;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class ContinuingEducationTable extends Table {
    private final MainLayout main;

    public ContinuingEducationTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();

//        private String courseId;
//        private String competencyEvaluationId;
//        private String courseScheduleId;
//        private Date date;

        addContainerProperty("Course Date", Date.class, null);
        addContainerProperty("Course", String.class, null);
        addContainerProperty("Competency Evaluation", String.class, null);
        addContainerProperty("Venue", String.class, null);
        addContainerProperty("Details", Button.class, null);

        Set<PersonContinuingEducation> personContinuingEducations = PeopleFacade.personContinuiningEducation.findAll(personId);

        personContinuingEducations.parallelStream().forEach(item -> {

            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {


            });


            addItem(new Object[]{
                    item.getDate(),
                    course(item.getCourseId()),
                    evaluation(item.getCompetencyEvaluationId()),
                    scheduledCourse(item.getCourseScheduleId()),
                    details
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String scheduledCourse(String courseScheduleId) {
        ScheduledCourse scheduledCourse = TrainingFacade.scheduledCourseService.findById(courseScheduleId);
        if (scheduledCourse != null)
            return scheduledCourse.getVenue();
        return "Type Not Set";


    }

    private String course(String statusId) {
        Course course = TrainingFacade.courseService.findById(statusId);
        if (course != null)
            return course.getCourseName();
        return "Type Not Set";

    }

    private String evaluation(String addressTypeId) {
        CompetencyEvaluation competencyEvaluation = TrainingFacade.competencyEvaluationService.findById(addressTypeId);
        if (competencyEvaluation != null)
            return competencyEvaluation.getCompTypeName();
        return "Type Not Set";
    }

}
