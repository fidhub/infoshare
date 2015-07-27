package infoshare.services.ContentType.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.ContentType;
import infoshare.services.ContentType.ContentTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by codex on 2015/06/25.
 */
@Service
@SpringComponent
public class ContentTypeServiceImpl implements ContentTypeService{

    static Map<String,ContentType>  contentTypes = null;

    public ContentTypeServiceImpl() {
        if(contentTypes == null){
            contentTypes = new HashMap<>();
            ContentType contentType = new ContentType.Builder("edited")
                    .description("un edited content posted by thule")
                    .id("1")
                    .build();
            ContentType contentType1 = new ContentType.Builder("raw")
                    .description("un edited content posted by thule")
                    .id("1")
                    .build();
            ContentType contentType2 = new ContentType.Builder("Published")
                    .description("un edited content posted by thule")
                    .id("1")
                    .build();
            contentTypes.put(contentType .getId(),contentType );
            contentTypes.put(contentType1.getId(),contentType1);
            contentTypes.put(contentType2.getId(),contentType2);
        }
    }

    @Override
    public ContentType find(String s) {
     return contentTypes.get(s);
    }

    @Override
    public ContentType save(ContentType entity) {
        contentTypes.put(entity.getId(),entity);
        return contentTypes.get(entity.getId());
    }
    @Override
    public ContentType merge(ContentType entity) {
        contentTypes.put(entity.getId(),entity);
        return contentTypes.get(entity.getId());
    }

    @Override
    public void remove(ContentType entity) {
        contentTypes.remove(entity.getId());
    }

    @Override
    public List<ContentType> findAll() {

        return new ArrayList<>(contentTypes.values());
    }
}
