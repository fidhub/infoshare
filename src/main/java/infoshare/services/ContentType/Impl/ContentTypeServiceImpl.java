package infoshare.services.ContentType.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.restapi.RestApiConnectorClass;
import infoshare.restapi.UrlPath;
import infoshare.domain.ContentType;
import infoshare.services.ContentType.ContentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by codex on 2015/06/25.
 */
@Service
@SpringComponent
public class ContentTypeServiceImpl implements ContentTypeService{

    @Override
    public ContentType find(String s) {
     return RestApiConnectorClass.read(UrlPath.ContentTypeLinks.GET_ID, s, ContentType.class);
    }

    @Override
    public ContentType save(ContentType entity) {
        return RestApiConnectorClass.create(UrlPath.ContentTypeLinks.POST, entity, ContentType.class);
    }
    @Override
    public ContentType merge(ContentType entity) {
        return RestApiConnectorClass.update(UrlPath.ContentTypeLinks.PUT,entity);
    }

    @Override
    public void remove(ContentType entity) {
    }

    @Override
    public List<ContentType> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.ContentTypeLinks.GETALL,ContentType.class);
    }
}
