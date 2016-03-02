package infoshare.services.Content.Impl;

import infoshare.domain.content.PublishedContent;
import infoshare.restapi.Content.PublishedContentAPI;
import infoshare.services.Content.PublishedContentService;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
public class PublishedContentServiceImpl implements PublishedContentService {

    @Override
    public PublishedContent save(PublishedContent entity) {
        return PublishedContentAPI.save(entity);
    }

    @Override
    public PublishedContent update(PublishedContent entity) {
        return PublishedContentAPI.update(entity);
    }

    @Override
    public PublishedContent findById(String org, String id) {
        return PublishedContentAPI.findById(org,id);
    }

    @Override
    public void delete(PublishedContent entity) {
        PublishedContentAPI.update(entity);
    }

    @Override
    public Set<PublishedContent> findAll(String org) {
        return PublishedContentAPI.findAll(org);
    }
}
