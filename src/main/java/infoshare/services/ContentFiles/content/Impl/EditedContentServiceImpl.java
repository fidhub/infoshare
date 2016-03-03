package infoshare.services.ContentFiles.content.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.EditedContent;
import infoshare.restapi.ContentFiles.content.EditedContentAPI;
import infoshare.services.ContentFiles.content.EditedContentService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
@Service
@SpringComponent
public class EditedContentServiceImpl implements EditedContentService {

    @Override
    public EditedContent save(EditedContent entity) {
        return EditedContentAPI.save(entity);
    }

    @Override
    public EditedContent update(EditedContent entity) {
        return EditedContentAPI.update(entity);
    }

    @Override
    public EditedContent findById(String org, String id) {
        return EditedContentAPI.findById(org,id);
    }

    @Override
    public void delete(EditedContent entity) {
        EditedContentAPI.update(entity);
    }

    @Override
    public Set<EditedContent> findAll(String org) {
        return EditedContentAPI.findAll(org);
    }
}
