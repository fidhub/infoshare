package infoshare.services.ContentFiles.Media;

import infoshare.domain.content.Media;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Component
public interface MediaService {
    Media findById(String org,String id);
    Media save(Media entity);
    Media update(Media entity);
    void delete(Media entity);
    Set<Media> findAll(String org);
}
