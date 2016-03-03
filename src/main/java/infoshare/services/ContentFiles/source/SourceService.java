package infoshare.services.ContentFiles.source;

import infoshare.domain.content.Source;
import infoshare.restapi.ContentFiles.Source.SourceAPI;
import infoshare.services.Services;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by codex on 2015/06/27.
 */
@Component
public interface SourceService  {
     Source findById(String org,String id);
     Source save(Source entity);
     Source update(Source entity);
     void delete(Source entity);
     Set<Source> findAll(String org);
}
