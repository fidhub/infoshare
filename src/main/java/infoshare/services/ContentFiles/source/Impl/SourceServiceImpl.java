package infoshare.services.ContentFiles.source.Impl;

import infoshare.domain.content.Source;
import infoshare.restapi.ContentFiles.Source.SourceAPI;
import infoshare.services.ContentFiles.source.SourceService;

import java.util.Set;

/**
 * Created by codex on 2015/06/27.
 *//*
@Service
@SpringComponent*/
public class SourceServiceImpl implements SourceService {

    private static SourceServiceImpl sourceService=null;

    private SourceServiceImpl(){}

    public  static SourceServiceImpl getInstance(){
        if(sourceService==null) {
            return new SourceServiceImpl();
        }
        return sourceService;
    }

    @Override
    public Source findById(String org, String id) {
        return SourceAPI.findById(org, id);
    }

    @Override
    public Source save(Source entity) {
        return SourceAPI.save(entity);
    }

    @Override
    public Source update(Source entity) {
        return SourceAPI.update(entity);
    }

    @Override
    public void delete(Source entity) {
        SourceAPI.update(entity);
    }

    @Override
    public Set<Source> findAll(String org) {
        return SourceAPI.findAll(org);
    }
}
