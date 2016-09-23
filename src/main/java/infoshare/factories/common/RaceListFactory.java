package infoshare.factories.common;


import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Race;

/**
 * Created by zamzam on 15/08/22.
 */
public class RaceListFactory {
    public static Race getRaceList(String raceName) {
        Race race = new Race.Builder()
                .name(raceName)
                .id(KeyGenerator.getEntityId())
                .state(DomainState.ACTIVE.name())
                .build();
        return race;
    }
}
