package infoshare.services.RawContent.Impl;

import infoshare.restapi.RestApiConnectorClass;
import infoshare.domain.RawContent;
import infoshare.services.RawContent.RawContentService;

import java.util.List;

/**
 * Created by user9 on 2016/02/12.
 */
public class RawContentServiceImpl implements RawContentService {
    @Override
    public RawContent find(String s) {
        return RestApiConnectorClass.read(UrlPath.RawLinks.GET_ID, s, RawContent.class);
    }

    @Override
    public RawContent save(RawContent entity) {
        return RestApiConnectorClass.create(UrlPath.RawLinks.POST, entity, RawContent.class);
    }

    @Override
    public RawContent merge(RawContent entity) {
        return RestApiConnectorClass.update(UrlPath.RawLinks.PUT,entity);
    }

    @Override
    public void remove(RawContent entity) {

    }

    @Override
    public List<RawContent> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.RawLinks.GETALL,RawContent.class);
    }
}
