package infoshare.factories.demographics;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Race;

import java.util.Map;

/**
 * Created by Songezo on 2016-03-02.
 */
public class RaceFactory {
    public static Race getrace (Map<String, String> raceVals){
        Race race = new Race.Builder()
                .id(KeyGenerator.getEntityId())
                .name(raceVals.get("name"))
                .state(DomainState.ACTIVE.name())
                .build();
        return race;
    }
}
