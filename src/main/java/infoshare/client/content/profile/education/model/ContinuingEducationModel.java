package infoshare.client.content.profile.education.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/20.
 */
public class ContinuingEducationModel implements Serializable {
    private String courseId;
    private String competencyEvaluationId;
    private String courseScheduleId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCompetencyEvaluationId() {
        return competencyEvaluationId;
    }

    public void setCompetencyEvaluationId(String competencyEvaluationId) {
        this.competencyEvaluationId = competencyEvaluationId;
    }

    public String getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(String courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }
}
