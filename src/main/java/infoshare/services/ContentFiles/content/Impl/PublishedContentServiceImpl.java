package infoshare.services.ContentFiles.content.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.PublishedContent;
import infoshare.restapi.ContentFiles.content.PublishedContentAPI;
import infoshare.services.ContentFiles.content.PublishedContentService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
@Service
@SpringComponent
public class PublishedContentServiceImpl implements PublishedContentService {

    @Override
    public PublishedContent save(PublishedContent entity) {
        return PublishedContentAPI.save(entity);
    }

    @Override
    public PublishedContent update(PublishedContent entity) {
        return PublishedContentAPI.update(entity);
    }

    @Override
    public PublishedContent findById(String org, String id) {
        return PublishedContentAPI.findById(org,id);
    }

    @Override
    public void delete(PublishedContent entity) {
        PublishedContentAPI.update(entity);
    }

    @Override
    public Set<PublishedContent> findAll(String org) {
        return PublishedContentAPI.findAll(org);
    }
}
