package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonDemographics;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonDemographicsFactory {
    public static PersonDemographics getPersonDemographics(Map<String,Date> dates, Map<String, String> personDemographicsVals, int numberOfDependencies){
        PersonDemographics personDemographics = new PersonDemographics.Builder()
                .id(KeyGenerator.getEntityId())
                .genderId(personDemographicsVals.get("genderId"))
                .personId(personDemographicsVals.get("personId"))
                .dateOfBirth(dates.get("dateOfBirth"))
                .maritalStatusId(personDemographicsVals.get("maritalStatusId"))
                .numberOfDependencies(numberOfDependencies)
                .date(dates.get("date"))
                .state(DomainState.ACTIVE.name())
                .build();

        return personDemographics;

    }
}
