package infoshare.services.source.sourceServiceImpl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.restapi.RestApiConnectorClass;
import infoshare.domain.Source;
import infoshare.services.source.SourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by codex on 2015/06/27.
 */
@Service
@SpringComponent
public class SourceServiceImpl implements SourceService {

    @Override
    public Source find(String s) {
        return RestApiConnectorClass.read(UrlPath.SourceLinks.GET_ID,s,Source.class);
    }

    @Override
    public Source save(Source entity) {
     return RestApiConnectorClass.create(UrlPath.SourceLinks.POST,entity,Source.class);
    }

    @Override
    public Source merge(Source entity) {
       return RestApiConnectorClass.update(UrlPath.SourceLinks.PUT,entity);
    }

    @Override
    public void remove(Source entity) {
    }

    @Override
    public List<Source> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.SourceLinks.GETALL,Source.class);
    }
}
