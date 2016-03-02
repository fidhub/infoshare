package infoshare.services.Content.Impl;

import infoshare.domain.content.EditedContent;
import infoshare.restapi.Content.EditedContentAPI;
import infoshare.services.Content.EditedContentService;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
public class EditedContentServiceImpl implements EditedContentService {

    @Override
    public EditedContent findById(String s) {
        return EditedContentAPI.findById(s);
    }

    @Override
    public EditedContent save(EditedContent entity) {
        return EditedContentAPI.save(entity);
    }

    @Override
    public EditedContent update(EditedContent entity) {
        return EditedContentAPI.update(entity);
    }

    @Override
    public void delete(EditedContent entity) {
        EditedContentAPI.update(entity);
    }

    @Override
    public Set<EditedContent> findAll() {
        return EditedContentAPI.findAll();
    }
}
