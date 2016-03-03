package infoshare.services.ContentFiles.Media.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.Media;
import infoshare.restapi.ContentFiles.Media.MediaAPI;
import infoshare.services.ContentFiles.Media.MediaService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Service
@SpringComponent
public class MediaServiceImpl implements MediaService {
    @Override
    public Media findById(String org, String id) {
        return MediaAPI.findById(org, id);
    }

    @Override
    public Media save(Media entity) {
        return MediaAPI.save(entity);
    }

    @Override
    public Media update(Media entity) {
        return MediaAPI.update(entity);
    }

    @Override
    public void delete(Media entity) {
        MediaAPI.update(entity);
    }

    @Override
    public Set<Media> findAll(String org) {
        return MediaAPI.findAll(org);
    }
}
