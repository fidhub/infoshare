package infoshare.services.PublishedContent.Impl;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.domain.PublishedContent;
import infoshare.services.PublishedContent.PublishedContentService;

import java.util.List;

/**
 * Created by user9 on 2016/02/12.
 */
public class PublishedContentServiceImpl implements PublishedContentService {
    @Override
    public PublishedContent find(String s) {
        return RestApiConnectorClass.read(UrlPath.PublishedLinks.GET_ID, s, PublishedContent.class);
    }

    @Override
    public PublishedContent save(PublishedContent entity) {
        return RestApiConnectorClass.create(UrlPath.PublishedLinks.POST,entity,PublishedContent.class);
    }

    @Override
    public PublishedContent merge(PublishedContent entity) {
        return RestApiConnectorClass.update(UrlPath.PublishedLinks.PUT,entity);
    }

    @Override
    public void remove(PublishedContent entity) {

    }

    @Override
    public List<PublishedContent> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.PublishedLinks.GETALL,PublishedContent.class);
    }
}
