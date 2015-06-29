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
    @Override
    public Source find(String s) {
        return null;
    }

    @Override
    public Source save(Source entity) {
        return null;
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
        List<Source> sources = new ArrayList<>();

        Source source = new Source.Builder("Edited")
                .description("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod")
                .build();
        sources.add(source);
        return sources;
    }
}
