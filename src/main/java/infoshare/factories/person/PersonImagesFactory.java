package infoshare.factories.person;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonImages;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonImagesFactory {

    public static PersonImages getPersonImages(Map<String, String> personImagesVals, Date date, Option<String> size){
        PersonImages personImages = new PersonImages.Builder()
                .org(personImagesVals.get("org"))
                .personId(personImagesVals.get("personId"))
                .id(KeyGenerator.getEntityId())
                .url(personImagesVals.get("url"))
                .description(personImagesVals.get("description"))
                .mime(personImagesVals.get("mime"))
                .size(size)
                .date(date)
                .build();
        return personImages;
    }
}
