package infoshare.domain.person;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class PersonDemographics implements Serializable, Comparable<PersonDemographics> {
    private String id;
    private String personId;
    private String genderId;
    private String dateOfBirth;
    private String maritalStatusId;
    private int numberOfDependencies;
    private Date date;
    private String state;

    private PersonDemographics(){
    }
    public PersonDemographics(Builder builder){
        this.id = builder.id;
        this.personId = builder.personId;
        this.genderId = builder.genderId;
        this.dateOfBirth = builder.dateOfBirth;
        this.maritalStatusId = builder.maritalStatusId;
        this.numberOfDependencies = builder.numberOfDependencies;
        this.date = builder.date;
        this.state = builder.state;
    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getGenderId() {
        return genderId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public int getNumberOfDependencies() {
        return numberOfDependencies;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private String id;
        private String personId;
        private String genderId;
        private String dateOfBirth;
        private String maritalStatusId;
        private int numberOfDependencies;
        private Date date;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return  this;
        }

        public Builder personId(String personId) {
            this.personId = personId;
            return this;
        }

        public Builder genderId(String genderId) {
            this.genderId = genderId;
            return this;
        }

        public Builder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder maritalStatusId(String maritalStatusId) {
            this.maritalStatusId = maritalStatusId;
            return this;
        }

        public Builder numberOfDependencies(int numberOfDependencies) {
            this.numberOfDependencies = numberOfDependencies;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder copy(PersonDemographics personDemographics){
            this.id = personDemographics.id;
            this.personId = personDemographics.personId;
            this.genderId = personDemographics.genderId;
            this.dateOfBirth = personDemographics.dateOfBirth;
            this.maritalStatusId = personDemographics.maritalStatusId;
            this.numberOfDependencies = personDemographics.numberOfDependencies;
            this.date = personDemographics.date;
            this.state = personDemographics.state;
            return this;
        }
        public PersonDemographics build(){return new PersonDemographics(this);}
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(PersonDemographics personDemographics) {
        return dateOfBirth.compareTo(personDemographics.dateOfBirth);
    }
}
