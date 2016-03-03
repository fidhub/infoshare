package infoshare.factories.demographics;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Gender;

import java.util.Map;

/**
 * Created by Songezo on 2016-03-02.
 */
public class GenderFactory {
    public static Gender getGender (Map<String, String> genderVals){
        Gender gender = new Gender.Builder()
                .id(KeyGenerator.getEntityId())
                .name(genderVals.get("name"))
                .state(DomainState.ACTIVE.name())
                .build();
        return gender;
    }
}
