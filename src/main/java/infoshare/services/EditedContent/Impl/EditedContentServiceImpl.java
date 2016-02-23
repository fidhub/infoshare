package infoshare.services.EditedContent.Impl;

import infoshare.restapi.RestApiConnectorClass;
import infoshare.domain.EditedContent;
import infoshare.services.EditedContent.EditedContentService;

import java.util.List;

/**
 * Created by user9 on 2016/02/12.
 */
public class EditedContentServiceImpl implements EditedContentService {
    @Override
    public EditedContent find(String s) {
        return RestApiConnectorClass.read(UrlPath.EditedLinks.GET_ID, s, EditedContent.class);
    }

    @Override
    public EditedContent save(EditedContent entity) {
        return RestApiConnectorClass.create(UrlPath.EditedLinks.POST,entity,EditedContent.class);
    }

    @Override
    public EditedContent merge(EditedContent entity) {
        return RestApiConnectorClass.update(UrlPath.EditedLinks.PUT,entity);
    }

    @Override
    public void remove(EditedContent entity) {

    }

    @Override
    public List<EditedContent> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.EditedLinks.GETALL,EditedContent.class);
    }
}
