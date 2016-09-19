package infoshare.factories.content;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.Media;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class MediaFactory {

    public static Media getMedia(Map<String, String> mediaVals, Date date){
        Media media = new Media.Builder()
                .id(KeyGenerator.getEntityId())
                .contentid(mediaVals.get("contentId"))
                .description(mediaVals.get("description"))
                .url(mediaVals.get("url"))
                .mime(mediaVals.get("mime"))
                .state(DomainState.ACTIVE.name())
                .date(date)
                .build();
        return media;
    }
}
