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

    private List<ContentType> contentTypes = new ArrayList<>();
    @Override
    public ContentType find(String s) {
     return null;
    }

    @Override
    public ContentType save(ContentType entity) {
        return new ContentType.Builder(entity.getContentTyeName())
                    .contentTyeDescription(entity.getContentTyeDescription())
                    .id(entity.getId()).build();
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
        ContentType contentType = new ContentType.Builder("edited")
                    .contentTyeDescription("un edited content posted by thule")
                    .id("1")
                    .build();
        ContentType contentType1 = new ContentType.Builder("raw")
                    .contentTyeDescription("un edited content posted by thule")
                    .id("1")
                    .build();
          ContentType contentType2 = new ContentType.Builder("published")
                    .contentTyeDescription("un edited content posted by thule")
                    .id("1")
                    .build();
        contentTypes.add(contentType);
        contentTypes.add(contentType1);
        contentTypes.add(contentType2);
        return contentTypes;
    }
}
