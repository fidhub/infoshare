package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonDemographics;

import java.util.Date;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonDemographicsFactory {
    public static PersonDemographics getPersonDemographics(
            String personId, String genderId, Date dateOfBirth, String raceId){
        PersonDemographics personDemographics = new PersonDemographics.Builder()
                .id(KeyGenerator.getEntityId())
                .genderId(genderId)
                .personId(personId)
                .personraceid(raceId)
                .dateOfBirth(dateOfBirth)
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .build();

        return personDemographics;

    }
}
