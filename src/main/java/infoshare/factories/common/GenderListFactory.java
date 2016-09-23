package infoshare.factories.common;


import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Gender;

/**
 * Created by hashcode on 2015/08/16.
 */
public class GenderListFactory {
    public static Gender getGenderList(String gender) {
        Gender genderList = new Gender
                .Builder()
                .state(DomainState.ACTIVE.name())
                .id(KeyGenerator.getEntityId())
                .name(gender).build();
        return genderList;
    }
}