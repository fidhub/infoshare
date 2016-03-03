package infoshare.services.ContentFiles.source.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.Source;
import infoshare.restapi.ContentFiles.Source.SourceAPI;
import infoshare.services.ContentFiles.source.SourceService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by codex on 2015/06/27.
 */
@Service
@SpringComponent
public class SourceServiceImpl implements SourceService {

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
