package infoshare.services.Content.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.restapi.RestApiConnectorClass;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by codex on 2015/06/24.
 */
@Service
@SpringComponent
public class ContentServiceImp implements ContentService {

    @Override
    public Content find(String s) {
        try {
            return RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, s, Content.class);
        }catch (Exception ep){
            return null;
        }

    }

    @Override
    public Content save(Content entity) {
        return RestApiConnectorClass.create(UrlPath.ContentLinks.POST,entity,Content.class);
    }

    @Override
    public Content merge(Content entity) {
        return RestApiConnectorClass.update(UrlPath.ContentLinks.PUT,entity);
    }

    @Override
    public void remove(Content entity) {
    }

    @Override
    public List<Content> findAll() {
      return RestApiConnectorClass.readAll(UrlPath.ContentLinks.GETALL,Content.class);
    }
}
