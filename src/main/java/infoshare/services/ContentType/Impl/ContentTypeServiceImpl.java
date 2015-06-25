package infoshare.services.ContentType.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.ContentType;
import infoshare.services.ContentType.ContentTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codex on 2015/06/25.
 */
@Service
@SpringComponent
public class ContentTypeServiceImpl implements ContentTypeService{

    @Override
    public ContentType find(String s) {
        return null;
    }

    @Override
    public ContentType save(ContentType entity) {
        return null;
    }

    @Override
    public ContentType merge(ContentType entity) {
        return null;
    }

    @Override
    public void remove(ContentType entity) {

    }

    @Override
    public List<ContentType> findAll() {

        List<ContentType> contentTypes = new ArrayList<>();

        ContentType contentType = new ContentType.Builder("raw").description("22555").build();
        contentTypes.add(contentType);
        return contentTypes;
    }
}
