package infoshare.services.source.sourceServiceImpl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Source;
import infoshare.services.source.SourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codex on 2015/06/27.
 */
@Service
@SpringComponent
public class SourceServiceImpl implements SourceService {
    List<Source> sources = new ArrayList<>();
    public void addList(){
        Source source = new Source.Builder("source name")
                .description("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod")
                .id("1")
                .build();
        sources.add(source);
    }
    @Override
    public Source find(String s) {
        Source source = null;
        for(Source source1: sources )
            if (source1.getId().equalsIgnoreCase(s))
                source  = source1;

        return source;
    }

    @Override
    public Source save(Source entity) {
        return new Source.Builder(entity.getName())
                    .description(entity.getDescription())
                    .id(entity.getId()).build();
    }

    @Override
    public Source merge(Source entity) {
        return null;
    }

    @Override
    public void remove(Source entity) {

    }

    @Override
    public List<Source> findAll() {
        addList();
        return sources;
    }
}
