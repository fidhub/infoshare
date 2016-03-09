package infoshare.services.ContentFiles.content;

import infoshare.domain.content.RawContent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
@Component
public interface RawContentService {
    RawContent save( RawContent entity);
    RawContent update( RawContent entity);
    RawContent findById( String org,String id);
    void delete( RawContent entity);
    Set<RawContent> findAll(String org);
}
