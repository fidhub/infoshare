package infoshare.services.source.sourceServiceImpl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Source;
import infoshare.services.source.SourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by codex on 2015/06/27.
 */
@Service
@SpringComponent
public class SourceServiceImpl implements SourceService {
    static Map<String,Source> sources = null;

    public SourceServiceImpl() {
        if(sources == null) {
            sources = new HashMap<>();
            Source source = new Source.Builder("source name")
                    .description("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod")
                    .id("1")
                    .build();
            sources.put(source.getId(), source);
        }
    }
    @Override
    public Source find(String s) {
        return sources.get(s);
    }

    @Override
    public Source save(Source entity) {
        sources.put(entity.getId(),entity);
        return sources.get(entity.getId());
    }

    @Override
    public Source merge(Source entity) {
        sources.put(entity.getId(),entity);
        return sources.get(entity.getId());
    }

    @Override
    public void remove(Source entity) {
        sources.remove(entity.getId());
    }

    @Override
    public List<Source> findAll() {
        return new ArrayList<>(sources.values());
    }
}
