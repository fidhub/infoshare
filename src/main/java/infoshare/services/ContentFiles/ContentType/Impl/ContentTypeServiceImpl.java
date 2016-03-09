package infoshare.services.ContentFiles.ContentType.Impl;

import infoshare.domain.content.ContentType;
import infoshare.restapi.ContentFiles.ContentType.ContentTypeAPI;
import infoshare.services.ContentFiles.ContentType.ContentTypeService;
<<<<<<< HEAD:src/main/java/infoshare/services/ContentFiles/ContentType/Impl/ContentTypeServiceImpl.java
=======
import org.springframework.stereotype.Service;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9:src/main/java/infoshare/services/ContentFiles/ContentType/Impl/ContentTypeServiceImpl.java

import java.util.Set;

/**
 * Created by codex on 2015/06/25.
 */

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
