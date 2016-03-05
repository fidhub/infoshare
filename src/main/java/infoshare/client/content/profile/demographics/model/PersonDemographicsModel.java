package infoshare.client.content.profile.demographics.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonDemographicsModel implements Serializable {
    private String genderId;
    private Date dateOfBirth;
    private String maritalStatusId;
    private int numberOfDependencies;

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
