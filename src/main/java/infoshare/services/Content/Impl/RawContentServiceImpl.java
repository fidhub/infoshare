package infoshare.services.Content.Impl;

import infoshare.domain.content.RawContent;
import infoshare.restapi.Content.RawContentAPI;
import infoshare.services.Content.RawContentService;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
public class RawContentServiceImpl implements RawContentService {


    @Override
    public RawContent save(RawContent entity) {
        return RawContentAPI.save(entity);
    }

    @Override
    public RawContent update(RawContent entity) {
        return RawContentAPI.update(entity);
    }

    @Override
    public RawContent findById(String org, String id) {
        return RawContentAPI.findById(org,id);
    }

    @Override
    public void delete(RawContent entity) {
        RawContentAPI.save(entity);
    }

    @Override
    public Set<RawContent> findAll(String org) {
        return RawContentAPI.findAll(org);
    }

}
