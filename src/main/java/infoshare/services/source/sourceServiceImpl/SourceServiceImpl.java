package infoshare.services.source.sourceServiceImpl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.Source;
import infoshare.restapi.Source.SourceAPI;
import infoshare.services.source.SourceService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by codex on 2015/06/27.
 */
@Service
@SpringComponent
public class SourceServiceImpl implements SourceService {

    @Override
    public Source findById(String s) {
        return SourceAPI.findById(s);
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
    public Set<Source> findAll() {
        return SourceAPI.findAll();
    }
}
