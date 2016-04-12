package infoshare.client.content.profile.demographics.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonDemographicsModel implements Serializable {

    private String personId;
    @NotNull
    private String genderId;
    @NotNull
    private Date dateOfBirth;
    private Date date;
    private String state;
    @NotNull
    private String maritalStatusId;
    @NotNull
    private int numberOfDependencies;
    @NotNull
    private String race;

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public int getNumberOfDependencies() {
        return numberOfDependencies;
    }

    public void setNumberOfDependencies(int numberOfDependencies) {
        this.numberOfDependencies = numberOfDependencies;
    }
}
