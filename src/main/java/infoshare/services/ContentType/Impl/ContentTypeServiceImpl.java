package infoshare.services.ContentType.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.ContentType;
import infoshare.restapi.ContentType.ContentTypeAPI;
import infoshare.services.ContentType.ContentTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by codex on 2015/06/25.
 */
@Service
@SpringComponent
public class ContentTypeServiceImpl implements ContentTypeService{

    @Override
    public ContentType findById(String s) {
        return ContentTypeAPI.findById(s);
    }

    @Override
    public ContentType save(ContentType entity) {
        return ContentTypeAPI.save(entity);
    }

    @Override
    public ContentType update(ContentType entity) {
        return ContentTypeAPI.update(entity);
    }

    @Override
    public void delete(ContentType entity) {
        ContentTypeAPI.update(entity);
    }

    @Override
    public Set<ContentType> findAll() {
        return ContentTypeAPI.findAll();
    }
}
