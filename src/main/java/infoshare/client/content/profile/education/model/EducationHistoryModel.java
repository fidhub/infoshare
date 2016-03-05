package infoshare.client.content.profile.education.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/20.
 */
public class EducationHistoryModel implements Serializable {

    private String institutionName;
    private String institutionLocation;
    private int yearOfGraduation;
    private String educationTypeId;
    private String degreeId;
    private String notes;

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionLocation() {
        return institutionLocation;
    }

    public void setInstitutionLocation(String institutionLocation) {
        this.institutionLocation = institutionLocation;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(String educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
